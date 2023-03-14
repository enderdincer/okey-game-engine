package com.enderdincer.okey.game.engine.event.validator

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState

interface GameEventValidator {

    fun validateCreateGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateAddPlayerGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateDeclareWinGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateDiscardTileGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateStartGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateDrawTileFromDiscardTileStackGameEvent(gameState: GameState, gameEvent: GameEvent)
    fun validateDrawTileFromCenterTileStackGameEvent(gameState: GameState, gameEvent: GameEvent)
}