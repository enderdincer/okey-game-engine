package com.enderdincer.okey.game.engine.handler

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator
import com.enderdincer.okey.game.engine.evaluator.RackEvaluator

class DefaultGameEventHandler(
        override val rackEvaluator: RackEvaluator
) : BaseGameEventHandler(rackEvaluator)