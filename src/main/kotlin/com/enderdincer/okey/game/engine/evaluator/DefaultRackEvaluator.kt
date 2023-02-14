package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

class DefaultRackEvaluator: RackEvaluator {

    override fun evaluate(rack: List<Tile>): RackEvalResult {
        return RackEvalResult()
    }
}