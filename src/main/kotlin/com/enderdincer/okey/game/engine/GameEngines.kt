package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.event.handler.GameEventHandlers

object GameEngines {

    @JvmStatic
    fun defaultGameEngine(): GameEngine = DefaultGameEngine(GameEventHandlers.defaultGameEventHandler())
}