package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.evaluator.set.TileSetEvaluator
import com.enderdincer.okey.game.engine.model.GameConfig

abstract class BaseRackEvaluator(
        protected open val gameConfig: GameConfig,
        protected open val tileSetEvaluator: TileSetEvaluator
): RackEvaluator