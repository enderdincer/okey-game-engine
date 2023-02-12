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
                GameEventType.START_GAME -> gameEventHandler.handleStartGame(prevGameState, gameEvent)
                GameEventType.DRAW_TILE_FROM_CENTER_TILE_STACK -> gameEventHandler.handleDrawTileFromCenterTileStack(prevGameState, gameEvent)
                GameEventType.DRAW_TILE_FROM_DISCARD_TILE_STACK -> gameEventHandler.handleDrawTileFromDiscardTileStack(prevGameState, gameEvent)
                GameEventType.DISCARD_TILE -> gameEventHandler.handleDiscardTile(prevGameState, gameEvent)
                GameEventType.DECLARE_WIN -> gameEventHandler.handleDeclareWin(prevGameState, gameEvent)
            }
}