package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class DeclareWinGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateDeclareWinGameEvent(prevGameState, gameEvent)

        val playerId = gameEvent.players?.get(0)?.playerId!!
        val winningPlayer = prevGameState.players!!.find { it.playerId == playerId }!!

        return prevGameState.copy(isGameOverByPlayerWin = true, winningPlayer = winningPlayer)
    }
}