package com.infotechplatform.okey.game.engine.handler

import com.infotechplatform.okey.game.engine.Utils
import com.infotechplatform.okey.game.engine.exception.InvalidGameConfigException
import com.infotechplatform.okey.game.engine.model.*
import com.infotechplatform.okey.game.experimental.TileHandler
import java.lang.RuntimeException
import java.util.Random

abstract class BaseGameEventHandler : GameEventHandler {

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


        return prevGameState.copy(centerTileStack = centerTileStack)
    }

    override fun handleDetermineJoker(prevGameState: GameState, gameEvent: GameEvent): GameState {
        val gameConfig = prevGameState.gameConfig
                ?: throw InvalidGameConfigException("Game configuration is null. First initiate the game with INIT_GAME event.")

        val centerTileStack = prevGameState.centerTileStack
                ?: throw RuntimeException("Center tile stack is null. First initiate the game with INIT_GAME event.")

        val randomTileIndex = Random().nextInt(gameConfig.getTotalNumberOfTiles())
        val joker = centerTileStack[randomTileIndex]

        return prevGameState.copy(joker = joker)
    }

    override fun handleAddPlayer(prevGameState: GameState, gameEvent: GameEvent): GameState {
        if (prevGameState.players == null) {
            return prevGameState.copy(players = gameEvent.players)
        }

            val updatedPlayers = prevGameState.players.toMutableList()
        updatedPlayers.addAll(gameEvent.players)

        return prevGameState.copy(players = updatedPlayers)
    }

    override fun handleDeclareWin(prevGameState: GameState, gameEvent: GameEvent): GameState {
        return prevGameState
    }

    override fun handleDiscardTile(prevGameState: GameState, gameEvent: GameEvent): GameState {

        TileHandler.discardTile()
        return prevGameState
    }

    override fun handleDrawTile(prevGameState: GameState, gameEvent: GameEvent): GameState {
        return prevGameState
    }
}