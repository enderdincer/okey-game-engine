package com.infotechplatform.okey.game.engine

import com.infotechplatform.okey.game.model.GameEvent
import com.infotechplatform.okey.game.handler.GameEventHandler
import com.infotechplatform.okey.game.model.GameEventType
import com.infotechplatform.okey.game.model.GameState

object GameEngine {

    fun getNextGameState(prevGameState: GameState, gameEvent: GameEvent): GameState {
        return when (gameEvent.type) {
            GameEventType.INIT_GAME -> GameEventHandler.handleInitGame(prevGameState)
            GameEventType.DECLARE_WIN -> GameEventHandler.handleDeclareWin(prevGameState)
            else -> throw Exception("Unknown action type")
        }
    }

}