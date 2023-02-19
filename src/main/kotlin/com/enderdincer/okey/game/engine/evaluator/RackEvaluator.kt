package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

interface RackEvaluator {

    fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult

}