package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class DrawFromDiscardTileStackGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateDrawTileFromDiscardTileStackGameEvent(prevGameState, gameEvent)

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
}