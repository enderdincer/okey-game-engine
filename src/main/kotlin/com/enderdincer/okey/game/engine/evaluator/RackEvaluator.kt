package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

interface RackEvaluator {

    fun evaluate(rack: List<Tile>, joker: Tile, rackSize: Int = 15): RackEvalResult

    fun isRackWinning(sets: List<List<Tile>>, discardedTile: Tile, joker: Tile): Boolean
}