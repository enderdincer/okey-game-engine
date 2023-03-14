package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState

interface GameEventHandler {

    fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState
}