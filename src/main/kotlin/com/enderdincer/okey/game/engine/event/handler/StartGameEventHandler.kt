package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class StartGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateStartGameEvent(prevGameState, gameEvent)

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
}