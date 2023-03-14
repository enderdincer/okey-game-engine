package com.enderdincer.okey.game.engine

object GameEngines {

    private val DEFAULT_GAME_ENGINE = DefaultGameEngine()

    @JvmStatic
    fun getDefaultGameEngine() = GameEngines.getDefaultGameEngine(true)

    @JvmStatic
    fun getDefaultGameEngine(isSingleton: Boolean = true) =
            if (isSingleton) DEFAULT_GAME_ENGINE
            else DefaultGameEngine()

}