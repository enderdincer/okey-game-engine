package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState

interface GameEventHandler {

    fun handleCreateGame(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleAddPlayer(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleStartGame(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDeclareWin(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDrawTileFromCenterTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDrawTileFromDiscardTileStack(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDiscardTile(prevGameState: GameState, gameEvent: GameEvent): GameState
}