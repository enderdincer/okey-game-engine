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

    override fun isRackWinning(sets: List<List<Tile>>, discardedTile: Tile, joker: Tile): Boolean =
            sets.all { isValidSet(it, joker) } || isWinningByPairs(sets, joker)

    private fun isValidSet(set: List<Tile>, joker: Tile): Boolean {
        if (set.size < 3 || set.size > gameConfig.numberOfTilesInRack) {
            return false
        }

        var updatedSet = set

        if (set.contains(joker)) {
            val setWithoutJoker = set.filter { it != joker }

            if (setWithoutJoker.size == 1) {
                return true
            }

            val isRemainingSameColor = setWithoutJoker.all { it.color == setWithoutJoker[0].color }

            if (isRemainingSameColor) {
                val newSet = mutableSetOf<Tile>()
                for (tile in set) {
                    if (tile == joker) {
                        newSet.add(Tile(newSet.last().number!! + 1, newSet.last().color))
                    } else {
                        newSet.add(tile)
                    }
                }
            }

        }

        val isSameColor = set.all { it.color == set[0].color }

        var isConsecutive = true
        for (i in set.indices) {
            if (i < set.size - 2) {
                isConsecutive = isConsecutive && (set[i].number!! + 1 == set[i + 1].number)
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

    private fun isWinningByPairs(sets: List<List<Tile>>, joker: Tile): Boolean {
        val isWinByPairsWithNoJoker = sets.all { it.size == 2 && it[0] == it[1] }
        return false
    }

    override fun evaluate(rack: List<Tile>, joker: Tile, rackSize: Int): RackEvalResult {
        val rackEvalResult = RackEvalResult()
        updateRackEvalResult(rack, joker, rackEvalResult, RackArrangement(), rackSize)
        return rackEvalResult
    }

    private fun updateRackEvalResult(tiles: List<Tile>, joker: Tile, rackEvalResult: RackEvalResult, temp: RackArrangement, rackSize: Int) {
        val allSets = tileSetEvaluator.findAllSets(tiles, joker)
        if (allSets.isEmpty()) {
            temp.unusedTiles.addAll(tiles)
            if (temp.getTotalTileNumber() == rackSize) {
                rackEvalResult.allRackArrangements.add(RackArrangement.of(temp))
            }
            temp.unusedTiles.clear()
            temp.sets.clear()
        } else {
            for (set in allSets) {
                temp.sets.add(set)
                val remainingTiles = removeTiles(tiles, set)
                updateRackEvalResult(remainingTiles, joker, rackEvalResult, temp, rackSize)
            }
        }
    }
}