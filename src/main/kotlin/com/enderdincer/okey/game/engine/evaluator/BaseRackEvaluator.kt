package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.GameConfig

abstract class BaseRackEvaluator(
        protected open val gameConfig: GameConfig
): RackEvaluator