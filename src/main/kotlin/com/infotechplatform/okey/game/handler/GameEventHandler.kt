package com.infotechplatform.okey.game.handler

import com.infotechplatform.okey.game.model.GameState

object GameEventHandler {

    fun handleInitGame(prevGameState: GameState): GameState {
        return prevGameState
    }

    fun handleDeclareWin(prevGameState: GameState): GameState {
        return prevGameState
    }
}