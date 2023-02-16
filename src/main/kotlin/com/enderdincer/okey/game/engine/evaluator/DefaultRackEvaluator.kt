package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.GameConfig
import com.enderdincer.okey.game.engine.model.RackArrangement
import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

class DefaultRackEvaluator(
        override val gameConfig: GameConfig
) : BaseRackEvaluator(gameConfig) {

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val allPossibleRackArrangements = findAllPossibleRackArrangements(rack, joker)
        return RackEvalResult(
                isWinning = isWinning(allPossibleRackArrangements),
                allPossibleRackArrangements = allPossibleRackArrangements,
                bestArrangementByRemainingTiles = bestArrangementByRemainingTiles(allPossibleRackArrangements)
        )
    }


    private fun findAllPossibleRackArrangements(rack: List<Tile>, joker: Tile): List<RackArrangement> {
        val allArrangements = mutableListOf<RackArrangement>()
        allArrangements.addAll(findAllGroups(rack, joker))
        allArrangements.addAll(findAllRuns(rack, joker))
        return allArrangements
    }

    private fun findAllGroups(rack: List<Tile>, joker: Tile): List<RackArrangement> {
        return listOf()
    }

    private fun findAllGroupsByMinSize(rack: List<Tile>, joker: Tile, minSize: Int): List<RackArrangement> {
        (1 until gameConfig.numberOfTilesPerTileColor).forEach { index ->
            // TODO
            rack.filter { tile -> tile.number == index }.filter { tile -> true }
        }
        return listOf()
    }

    private fun findAllRuns(rack: List<Tile>, joker: Tile): List<RackArrangement> {
        return listOf()
    }


    private fun bestArrangementByRemainingTiles(allPossibleRackArrangements: List<RackArrangement>): RackArrangement {
        return RackArrangement(listOf(), listOf())
    }

    private fun isWinning(allPossibleRackArrangements: List<RackArrangement>): Boolean {
        return false
    }


}