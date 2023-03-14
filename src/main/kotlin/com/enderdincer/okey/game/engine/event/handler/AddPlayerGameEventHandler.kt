package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class AddPlayerGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateAddPlayerGameEvent(prevGameState, gameEvent)

        if (prevGameState.players == null) {
            return prevGameState.copy(players = gameEvent.players)
        }

        // TODO VALIDATION - CANT ADD MORE THAN MAX PLAYERS
        val updatedPlayers = prevGameState.players.toMutableList()
        updatedPlayers.addAll(gameEvent.players!!)

        return prevGameState.copy(players = updatedPlayers)
    }

}