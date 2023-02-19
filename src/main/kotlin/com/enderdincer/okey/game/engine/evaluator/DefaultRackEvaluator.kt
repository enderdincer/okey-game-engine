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
        return rackEvalResult
    }

    private fun updateRackEvalResult(tiles: List<Tile>, joker: Tile, rackEvalResult: RackEvalResult, current: RackArrangement) {
        val allSets = tileSetEvaluator.findAllSets(tiles, joker)
        if (allSets.isEmpty()) {
            current.unusedTiles.addAll(tiles)
            if (current.getTotalTileNumber() == gameConfig.numberOfTilesInRack + 1) {
                rackEvalResult.allRackArrangements.add(RackArrangement.of(current))
                current.unusedTiles.clear()
                current.sets.clear()
            }
        } else {
            for (set in allSets) {
                current.sets.add(set)
                val remainingTiles = removeTiles(tiles, set)
                updateRackEvalResult(remainingTiles, joker, rackEvalResult, current)
            }
        }
    }
}