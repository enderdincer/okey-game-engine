package com.enderdincer.okey.game.engine.event.validator

object GameEventValidators {

    private val DEFAULT_GAME_EVENT_VALIDATOR = DefaultGameEventValidator()

    @JvmStatic
    fun getDefaultGameEventValidator() = DEFAULT_GAME_EVENT_VALIDATOR

    @JvmStatic
    fun getDefaultGameEventValidator(isSingleton: Boolean = true) =
            if (isSingleton) DEFAULT_GAME_EVENT_VALIDATOR
            else DefaultGameEventValidator()
}