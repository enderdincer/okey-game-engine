package com.enderdincer.okey.game.engine.event.validator

import com.enderdincer.okey.game.engine.evaluator.Evaluators

object GameEventValidators {

    private val DEFAULT_GAME_EVENT_VALIDATOR = DefaultGameEventValidator(Evaluators.getDefaultRackEvaluator())

    @JvmStatic
    fun getDefaultGameEventValidator() = DEFAULT_GAME_EVENT_VALIDATOR

    @JvmStatic
    fun getDefaultGameEventValidator(isSingleton: Boolean = true) =
            if (isSingleton) DEFAULT_GAME_EVENT_VALIDATOR
            else DefaultGameEventValidator(Evaluators.getDefaultRackEvaluator())
}