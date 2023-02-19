package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.commons.TileHelper.removeTiles
import com.enderdincer.okey.game.engine.evaluator.set.TileSetEvaluator
import com.enderdincer.okey.game.engine.model.GameConfig
import com.enderdincer.okey.game.engine.model.RackArrangement
import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

class DefaultRackEvaluator(
        override val gameConfig: GameConfig,
        override val tileSetEvaluator: TileSetEvaluator
) : BaseRackEvaluator(gameConfig, tileSetEvaluator) {

    override fun isRackWinning(sets: List<List<Tile>>, discardedTile: Tile): Boolean =
            sets.all { isValidSet(it) }

    private fun isValidSet(set: List<Tile>): Boolean {
        if (set.size < 3 || set.size > gameConfig.numberOfTilesInRack) {
            return false
        }

        val isSameColor = set.all { it.color == set[0].color }

        var isConsecutive = true
        for (i in set.indices) {
            if (i < set.size - 2) {
                isConsecutive = isConsecutive && set[i].number!! + 1 == set[i + 1].number
            }
        }

        if (isSameColor && isConsecutive) {
            return true
        }

        val isSameNumber = set.all { it.number == set[0].number }

        val hasNoDuplicate = set.size == set.distinct().size

        if (!isSameColor && isSameNumber && hasNoDuplicate) {
            return true
        }

        return false
    }

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val rackEvalResult = RackEvalResult()
        updateRackEvalResult(rack, joker, rackEvalResult, RackArrangement())
        return rackEvalResult
    }

    private fun updateRackEvalResult(tiles: List<Tile>, joker: Tile, rackEvalResult: RackEvalResult, temp: RackArrangement) {
        val allSets = tileSetEvaluator.findAllSets(tiles, joker)
        if (allSets.isEmpty()) {
            temp.unusedTiles.addAll(tiles)
            if (temp.getTotalTileNumber() == gameConfig.numberOfTilesInRack + 1) {
                rackEvalResult.allRackArrangements.add(RackArrangement.of(temp))
            }
            temp.unusedTiles.clear()
            temp.sets.clear()
        } else {
            for (set in allSets) {
                temp.sets.add(set)
                val remainingTiles = removeTiles(tiles, set)
                updateRackEvalResult(remainingTiles, joker, rackEvalResult, temp)
            }
        }
    }
}