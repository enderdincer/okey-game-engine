package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.domain.RackArrangement
import com.enderdincer.okey.game.engine.domain.Tile

interface RackEvaluator {

    fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult
}

data class RackEvalResult(
        var isWinning: Boolean = false,
        val allRackArrangements: MutableList<RackArrangement> = mutableListOf(),
)