package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.evaluator.group.DefaultTileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.DefaultTileRunEvaluator
import com.enderdincer.okey.game.engine.model.GameConfigs

object Evaluators {

    @JvmStatic
    fun getDefaultRackEvaluator(): RackEvaluator {
        val gameConfig = GameConfigs.getDefaultGameConfig()
        return DefaultRackEvaluator(
                gameConfig = gameConfig,
                tileGroupEvaluator = DefaultTileGroupEvaluator(gameConfig),
                tileRunEvaluator = DefaultTileRunEvaluator()
        )
    }
}