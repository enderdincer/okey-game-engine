package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.evaluator.group.TileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.TileRunEvaluator
import com.enderdincer.okey.game.engine.model.GameConfig

abstract class BaseRackEvaluator(
        protected open val gameConfig: GameConfig,
        protected open val tileGroupEvaluator: TileGroupEvaluator,
        protected open val tileRunEvaluator: TileRunEvaluator
): RackEvaluator