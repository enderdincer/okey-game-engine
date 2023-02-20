package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.evaluator.RackEvaluator
import com.enderdincer.okey.game.engine.exception.InvalidGameConfigException
import com.enderdincer.okey.game.engine.model.*
import java.util.*

abstract class BaseGameEventHandler(
        protected open val rackEvaluator: RackEvaluator
) : GameEventHandler {

    override fun handleCreateGame(prevGameState: GameState, gameEvent: GameEvent): GameState {
        if (prevGameState.gameId == null) {
            throw RuntimeException("Game ID is mandatory.")
        }

        val gameConfig = gameEvent.gameConfig
                ?: throw InvalidGameConfigException("Invalid game configuration on game init.")

        val centerTileStack = mutableListOf<Tile>()

        TileColor.values().forEach { tileColor ->
            (1..gameConfig.numberOfTilesPerTileColor).forEach { tileNumber ->
                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
            }
        }

        (1..gameConfig.numberOfFalseJokers).forEach { _ ->
            centerTileStack.add(Tile(tileType = TileType.FALSE_JOKER))
        }

        centerTileStack.shuffle()

        return prevGameState.copy(centerTileStack = centerTileStack, gameConfig = gameConfig)
    }

    override fun handleAddPlayer(prevGameState: GameState, gameEvent: GameEvent): GameState {
        if (prevGameState.players == null) {
            return prevGameState.copy(players = gameEvent.players)
        }

        // TODO VALIDATION - CANT ADD MORE THAN MAX PLAYERS
        val updatedPlayers = prevGameState.players.toMutableList()
        updatedPlayers.addAll(gameEvent.players!!)

        return prevGameState.copy(players = updatedPlayers)
    }

    override fun handleDeclareWin(prevGameState: GameState, gameEvent: GameEvent): GameState {
        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val winningPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        val rackEvalResult = rackEvaluator.evaluate(winningPlayer.rack!!, prevGameState.joker!!)
        val isWinning = rackEvalResult.isWinning

        if (!isWinning) {
            throw RuntimeException("Player has declared win but has no winning rack arrangement.")
        }

        return prevGameState.copy(isGameOverByPlayerWin = rackEvalResult.isWinning, winningPlayer = winningPlayer)
    }

    override fun handleDiscardTile(prevGameState: GameState, gameEvent: GameEvent): GameState {
        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")

        val updatedPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")

        updatedPlayer.discardTile(gameEvent.tile!!)

        return prevGameState.copy(
                players = getUpdatedPlayers(prevGameState.players!!, updatedPlayer)
        )
    }

    override fun handleDrawTileFromCenterTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState {
        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val drawingPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        val centerTileStack = prevGameState.centerTileStack?.toMutableList() ?: throw RuntimeException("")
        drawingPlayer.draw(sourceTileCollection = centerTileStack)
        return prevGameState.copy(
                players = getUpdatedPlayers(prevGameState.players, drawingPlayer),
                centerTileStack = centerTileStack
        )
    }

    override fun handleDrawTileFromDiscardTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState {
        val gameConfig = prevGameState.gameConfig ?: throw RuntimeException("")
        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val drawingPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        val leftPlayer = findLeftPlayer(prevGameState.players, drawingPlayer.index!!, gameConfig.numberOfPlayers)
                ?: throw RuntimeException("")
        val discardTileStack = leftPlayer.discardTileStack ?: throw RuntimeException("")

        drawingPlayer.draw(sourceTileCollection = discardTileStack)

        val updatedPlayers = prevGameState.players.map {
            when (it.playerId) {
                drawingPlayer.playerId -> drawingPlayer
                leftPlayer.playerId -> leftPlayer
                else -> it

            }
        }

        return prevGameState.copy(
                players = updatedPlayers,
        )
    }

    override fun handleStartGame(prevGameState: GameState, gameEvent: GameEvent): GameState {
        // validate start game with game config num of players

        // validate game config

        val gameConfig = prevGameState.gameConfig!!
        val centerTileStack = prevGameState.centerTileStack!!.toMutableList()

        val joker = determineJoker(gameConfig, centerTileStack)

        val updatedPlayers = (0 until prevGameState.players!!.size).map { index ->
            val player = prevGameState.players[index]
            player.discardTileStack = mutableListOf()
            player.rack = mutableListOf()
            player.index = index

            val numberOfTiles = if (index == 0) gameConfig.numberOfTilesInRack + 1 else gameConfig.numberOfTilesInRack
            player.draw(centerTileStack, times = numberOfTiles)

            player
        }

        return prevGameState.copy(players = updatedPlayers, joker = joker, centerTileStack = centerTileStack)
    }

    private fun findLeftPlayer(players: List<Player>, playerIndex: Int, numberOfPlayers: Int): Player? =
            players.find { it.index == getLeftPlayerIndex(playerIndex, numberOfPlayers) }

    private fun findRightPlayer(players: List<Player>, playerIndex: Int, numberOfPlayers: Int): Player? =
            players.find { it.index == getRightPlayerIndex(playerIndex, numberOfPlayers) }


    private fun determineJoker(gameConfig: GameConfig, centerTileStack: MutableList<Tile>): Tile {
        val randomTileIndex = Random().nextInt(gameConfig.getTotalNumberOfTiles())
        return centerTileStack[randomTileIndex]
    }

    private fun getUpdatedPlayers(players: List<Player>, updatedPlayer: Player): List<Player> =
            players.map { if (it.playerId == updatedPlayer.playerId) updatedPlayer else it }

    private fun getRightPlayerIndex(playerIndex: Int, numberOfPlayers: Int): Int {
        return (playerIndex + 1) % numberOfPlayers
    }

    private fun getLeftPlayerIndex(playerIndex: Int, numberOfPlayers: Int): Int {
        val remainder = (playerIndex - 1) % numberOfPlayers
        return if (remainder < 0) {
            remainder + numberOfPlayers
        } else {
            remainder
        }
    }
}