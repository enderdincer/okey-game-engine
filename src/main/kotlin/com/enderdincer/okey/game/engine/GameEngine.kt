package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.model.GameEvent
import com.enderdincer.okey.game.engine.model.GameState

interface GameEngine {

    fun getName(): String

    fun createGame(gameId: String): GameState

    fun getNextGameState(prevGameState: GameState, gameEvent: GameEvent): GameState

}