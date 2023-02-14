package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.handler.GameEventHandlers

object GameEngines {

    fun defaultGameEngine(): GameEngine = DefaultGameEngine(GameEventHandlers.defaultGameEventHandler())
}