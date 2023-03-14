package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class DrawFromCenterTileStackGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateDrawTileFromCenterTileStackGameEvent(prevGameState, gameEvent)

        val playerId = gameEvent.players?.get(0)?.playerId ?: throw RuntimeException("No player found.")
        val drawingPlayer = prevGameState.players!!.find { it.playerId == playerId } ?: throw RuntimeException("")
        val centerTileStack = prevGameState.centerTileStack?.toMutableList() ?: throw RuntimeException("")
        drawingPlayer.draw(sourceTileCollection = centerTileStack)

        return prevGameState.copy(
                players = getUpdatedPlayers(prevGameState.players, drawingPlayer),
                centerTileStack = centerTileStack
        )
    }
}