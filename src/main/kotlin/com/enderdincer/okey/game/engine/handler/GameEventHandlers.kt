package com.enderdincer.okey.game.engine.handler

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator

object GameEventHandlers {

    fun defaultGameEventHandler(): GameEventHandler = DefaultGameEventHandler(rackEvaluator = DefaultRackEvaluator())
}