package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.evaluator.Evaluators

object GameEventHandlers {

    private val DEFAULT_GAME_EVENT_HANDLER = DefaultGameEventHandler(Evaluators.getDefaultRackEvaluator())

    @JvmStatic
    fun getDefaultGameEventHandler() = getDefaultGameEventHandler(true)

    @JvmStatic
    fun getDefaultGameEventHandler(isSingleton: Boolean = true): GameEventHandler =
            if (isSingleton) DEFAULT_GAME_EVENT_HANDLER
            else DefaultGameEventHandler(Evaluators.getDefaultRackEvaluator())
}