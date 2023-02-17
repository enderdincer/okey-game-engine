package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator
import com.enderdincer.okey.game.engine.model.GameConfigs

object GameEventHandlers {

    @JvmStatic
    fun defaultGameEventHandler(): GameEventHandler = DefaultGameEventHandler(rackEvaluator = DefaultRackEvaluator(GameConfigs.getDefaultGameConfig()))
}