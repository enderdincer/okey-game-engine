package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.commons.Tiles.removeTiles
import com.enderdincer.okey.game.engine.evaluator.pair.TilePairEvaluator
import com.enderdincer.okey.game.engine.evaluator.set.TileSetEvaluator
import com.enderdincer.okey.game.engine.config.GameConfig
import com.enderdincer.okey.game.engine.domain.RackArrangement
import com.enderdincer.okey.game.engine.domain.Tile

class DefaultRackEvaluator(
        override val gameConfig: GameConfig,
        override val tileSetEvaluator: TileSetEvaluator,
        override val tilePairEvaluator: TilePairEvaluator
) : BaseRackEvaluator(gameConfig, tileSetEvaluator, tilePairEvaluator) {

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val rackEvalResult = RackEvalResult()
        updateResultWithSets(rack, joker, rackEvalResult, RackArrangement())
        rackEvalResult.allRackArrangements.add(getRackArrangementForPairs(rack, joker))
        val isWinning = isWinningBySets(rackEvalResult.allRackArrangements) || isWinningByPairs(rack, joker)
        return rackEvalResult.copy(isWinning = isWinning)
    }

    private fun isWinningBySets(rackArrangements: List<RackArrangement>): Boolean {
        val hasOnlyOneTileToDiscard = rackArrangements.filter { it.sets.isNotEmpty() }.any { it.unusedTiles.size == 1 }
        val canPickTileFromSetsToDiscard = rackArrangements.any { it.unusedTiles.isEmpty() && it.sets.any { set -> set.size > 3 } }
        return hasOnlyOneTileToDiscard || canPickTileFromSetsToDiscard
    }

    private fun isWinningByPairs(rack: List<Tile>, joker: Tile): Boolean {
        val numberOfJokers = rack.count { it == joker }
        val numberOfPairs = rack.asSequence().filter { it != joker }
                .groupingBy { it.toString() }.eachCount().values.count { it == 2 }

        return when (numberOfJokers) {
            0 -> numberOfPairs == 7
            1 -> numberOfPairs == 6
            2 -> numberOfPairs == 5 || numberOfPairs == 6
            else -> throw RuntimeException("More than 2 Jokers is not allowed!")
        }
    }

    private fun updateResultWithSets(tiles: List<Tile>, joker: Tile, rackEvalResult: RackEvalResult, temp: RackArrangement) {
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
                updateResultWithSets(remainingTiles, joker, rackEvalResult, temp)
            }
        }
    }

    private fun getRackArrangementForPairs(rack: List<Tile>, joker: Tile): RackArrangement {
        val pairArrangement = tilePairEvaluator.findAllPairs(rack, joker)
        val pairs = pairArrangement.filter { it.size == 2 }
        val unusedTiles = pairArrangement.filter { it.size == 1 }.flatten().toMutableList()
        return RackArrangement(
                unusedTiles = unusedTiles,
                pairs = pairs
        )
    }
}