package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.evaluator.pair.TilePairEvaluator
import com.enderdincer.okey.game.engine.evaluator.set.TileSetEvaluator
import com.enderdincer.okey.game.engine.model.GameConfig

abstract class BaseRackEvaluator(
        protected open val gameConfig: GameConfig,
        protected open val tileSetEvaluator: TileSetEvaluator,
        protected open val tilePairEvaluator: TilePairEvaluator
): RackEvaluator