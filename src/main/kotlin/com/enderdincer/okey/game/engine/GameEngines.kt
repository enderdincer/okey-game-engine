package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.event.handler.GameEventHandlers

object GameEngines {

    private val DEFAULT_GAME_ENGINE = DefaultGameEngine(GameEventHandlers.defaultGameEventHandler())

    @JvmStatic
    fun getDefaultGameEngine(isSingleton: Boolean = true) =
            if (isSingleton) DEFAULT_GAME_ENGINE
            else DefaultGameEngine(GameEventHandlers.defaultGameEventHandler())

}