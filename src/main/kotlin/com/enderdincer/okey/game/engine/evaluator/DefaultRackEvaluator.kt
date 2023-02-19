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

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val rackEvalResult = RackEvalResult()
        updateRackEvalResult(rack, joker, rackEvalResult, RackArrangement())
        return rackEvalResult.copy(isWinning = isWinning(rackEvalResult.allRackArrangements))
    }

    private fun isWinning(rackArrangements: List<RackArrangement>): Boolean {
        val hasOnlyOneTileToDiscard = rackArrangements.any { it.unusedTiles.size == 1 }
        val canPickTileFromSetsToDiscard = rackArrangements.any { it.unusedTiles.isEmpty() && it.sets.any { set -> set.size > 3 } }
        return hasOnlyOneTileToDiscard || canPickTileFromSetsToDiscard
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