package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.evaluator.RackEvaluator
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class DeclareWinGameEventHandler(
        private val rackEvaluator: RackEvaluator,
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateDeclareWinGameEvent(prevGameState, gameEvent)

        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val winningPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        val rackEvalResult = rackEvaluator.evaluate(winningPlayer.rack!!, prevGameState.joker!!)
        val isWinning = rackEvalResult.isWinning

        if (!isWinning) {
            throw RuntimeException("Player has declared win but has no winning rack arrangement.")
        }

        return prevGameState.copy(isGameOverByPlayerWin = rackEvalResult.isWinning, winningPlayer = winningPlayer)
    }
}