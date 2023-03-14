package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.evaluator.RackEvaluator
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class DiscardTileGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateDiscardTileGameEvent(prevGameState, gameEvent)

        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val updatedPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        updatedPlayer.discardTile(gameEvent.tile!!)

        return prevGameState.copy(
                players = getUpdatedPlayers(prevGameState.players!!, updatedPlayer)
        )
    }
}