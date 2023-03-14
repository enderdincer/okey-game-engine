package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.event.handler.GameEventHandlers

class DefaultGameEngine : GameEngine {

    override fun getName(): String = "Okey Game Engine"

    override fun createGame(gameId: String) = GameState(gameId = gameId)

    override fun getNextGameState(prevGameState: GameState, gameEvent: GameEvent): GameState =
            GameEventHandlers.getGameEventHandler(gameEvent.type).handle(prevGameState, gameEvent)
}