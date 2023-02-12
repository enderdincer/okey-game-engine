package com.infotechplatform.okey.game.engine.handler

import com.infotechplatform.okey.game.engine.model.GameEvent
import com.infotechplatform.okey.game.engine.model.GameState

interface GameEventHandler {

    fun handleCreateGame(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleAddPlayer(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleStartGame(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDeclareWin(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDrawTileFromCenterTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDrawTileFromDiscardTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDiscardTile(prevGameState: GameState, gameEvent: GameEvent): GameState
}