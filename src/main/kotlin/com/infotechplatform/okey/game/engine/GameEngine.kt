package com.infotechplatform.okey.game.engine

import com.infotechplatform.okey.game.engine.model.GameEvent
import com.infotechplatform.okey.game.engine.handler.GameEventHandler
import com.infotechplatform.okey.game.engine.model.GameEventType
import com.infotechplatform.okey.game.engine.model.GameState

class GameEngine(
        private val gameEventHandler: GameEventHandler
) {

    fun getNextGameState(prevGameState: GameState, gameEvent: GameEvent): GameState =
            when (gameEvent.type) {
                GameEventType.CREATE_GAME -> gameEventHandler.handleCreateGame(prevGameState, gameEvent)
                GameEventType.ADD_PLAYER -> gameEventHandler.handleAddPlayer(prevGameState, gameEvent)
                GameEventType.START_GAME -> prevGameState
                GameEventType.DETERMINE_JOKER -> gameEventHandler.handleDetermineJoker(prevGameState, gameEvent)
                GameEventType.DRAW_TILE -> gameEventHandler.handleDrawTile(prevGameState, gameEvent)
                GameEventType.DISCARD_TILE -> gameEventHandler.handleDiscardTile(prevGameState, gameEvent)
                GameEventType.DECLARE_WIN -> gameEventHandler.handleDeclareWin(prevGameState, gameEvent)
            }
}