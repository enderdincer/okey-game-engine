package com.enderdincer.okey.game.engine.evaluator.set

import com.enderdincer.okey.game.engine.domain.Tile
import com.enderdincer.okey.game.engine.evaluator.group.TileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.TileRunEvaluator

class DefaultTileSetEvaluator(
        private val tileGroupEvaluator: TileGroupEvaluator,
        private val tileRunEvaluator: TileRunEvaluator
): TileSetEvaluator {

    override fun findAllSets(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val allSets = mutableListOf<List<Tile>>()
        allSets.addAll(tileGroupEvaluator.findAllGroups(rack, joker))
        allSets.addAll(tileRunEvaluator.findAllRuns(rack, joker))
        return allSets.distinct()
    }
}