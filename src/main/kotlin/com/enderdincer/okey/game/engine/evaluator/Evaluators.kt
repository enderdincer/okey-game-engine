package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.evaluator.group.DefaultTileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.pair.DefaultTilePairEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.DefaultTileRunEvaluator
import com.enderdincer.okey.game.engine.evaluator.set.DefaultTileSetEvaluator
import com.enderdincer.okey.game.engine.config.GameConfigs

object Evaluators {

    private val DEFAULT_TILE_PAIR_EVALUATOR = DefaultTilePairEvaluator()
    private val DEFAULT_TILE_RUN_EVALUATOR = DefaultTileRunEvaluator()
    private val DEFAULT_TILE_GROUP_EVALUATOR = DefaultTileGroupEvaluator(GameConfigs.getDefaultGameConfig())
    private val DEFAULT_TILE_SET_EVALUATOR = DefaultTileSetEvaluator(DEFAULT_TILE_GROUP_EVALUATOR, DEFAULT_TILE_RUN_EVALUATOR)
    private val DEFAULT_RACK_EVALUATOR = DefaultRackEvaluator(
            GameConfigs.getDefaultGameConfig(),
            DEFAULT_TILE_SET_EVALUATOR,
            DEFAULT_TILE_PAIR_EVALUATOR
    )

    @JvmStatic
    fun getDefaultRackEvaluator() = getDefaultRackEvaluator(true)

    @JvmStatic
    fun getDefaultRackEvaluator(isSingleton: Boolean = true) =
            if (isSingleton) DEFAULT_RACK_EVALUATOR
            else DefaultRackEvaluator(GameConfigs.getDefaultGameConfig(), DEFAULT_TILE_SET_EVALUATOR, DEFAULT_TILE_PAIR_EVALUATOR)
}