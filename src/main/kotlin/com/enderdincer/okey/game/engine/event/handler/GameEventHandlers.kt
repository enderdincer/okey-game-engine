package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator

object GameEventHandlers {

    @JvmStatic
    fun defaultGameEventHandler(): GameEventHandler = DefaultGameEventHandler(rackEvaluator = DefaultRackEvaluator())
}