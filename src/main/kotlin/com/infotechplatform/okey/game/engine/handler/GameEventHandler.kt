package com.infotechplatform.okey.game.engine.handler

import com.infotechplatform.okey.game.engine.model.GameEvent
import com.infotechplatform.okey.game.engine.model.GameState

interface GameEventHandler {

    fun handleCreateGame(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDeclareWin(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDrawTile(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDiscardTile(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleAddPlayer(prevGameState: GameState, gameEvent: GameEvent): GameState

    fun handleDetermineJoker(prevGameState: GameState, gameEvent: GameEvent): GameState
}