package com.enderdincer.okey.game.engine.event.handler

object GameEventHandlers {

    private val DEFAULT_GAME_EVENT_HANDLER = DefaultGameEventHandler()

    @JvmStatic
    fun defaultGameEventHandler(isSingleton: Boolean = true): GameEventHandler =
            if (isSingleton) DEFAULT_GAME_EVENT_HANDLER
            else DefaultGameEventHandler()
}