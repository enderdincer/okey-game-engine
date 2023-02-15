package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.evaluator.RackEvaluator

class DefaultGameEventHandler(
        override val rackEvaluator: RackEvaluator
) : BaseGameEventHandler(rackEvaluator)