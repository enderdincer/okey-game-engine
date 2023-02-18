package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.commons.TileHelper.addTiles
import com.enderdincer.okey.game.engine.commons.ifTrue
import com.enderdincer.okey.game.engine.evaluator.group.DefaultTileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.group.TileGroupEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.DefaultTileRunEvaluator
import com.enderdincer.okey.game.engine.evaluator.run.TileRunEvaluator
import com.enderdincer.okey.game.engine.model.*

class DefaultRackEvaluator(
        override val gameConfig: GameConfig,
        override val tileGroupEvaluator: TileGroupEvaluator = DefaultTileGroupEvaluator(gameConfig),
        override val tileRunEvaluator: TileRunEvaluator = DefaultTileRunEvaluator()
) : BaseRackEvaluator(gameConfig, tileGroupEvaluator, tileRunEvaluator) {

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val allPossibleRackArrangements = findAllPossibleRackArrangements(rack, joker)
        return RackEvalResult(bestArrangementByRemainingTiles = RackArrangement(listOf(), listOf()))
//        return RackEvalResult(
//                isWinning = isWinning(allPossibleRackArrangements),
//                allPossibleRackArrangements = allPossibleRackArrangements,
//                bestArrangementByRemainingTiles = bestArrangementByRemainingTiles(allPossibleRackArrangements)
//        )
    }

    private fun findAllPossibleRackArrangements(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val allArrangements = mutableListOf<List<Tile>>()
//        allArrangements.addAll(tileGroupEvaluator.findAllGroups(rack, joker))
        allArrangements.addAll(tileRunEvaluator.findAllRuns(rack, joker))
        return allArrangements
    }

    private fun bestArrangementByRemainingTiles(allPossibleRackArrangements: List<RackArrangement>): RackArrangement {
        return RackArrangement(listOf(), listOf())
    }

    private fun isWinning(allPossibleRackArrangements: List<RackArrangement>): Boolean {
        return false
    }

    private fun toArrangement(tiles: List<Tile>): RackArrangement {
        return RackArrangement(listOf(), listOf())
    }
}