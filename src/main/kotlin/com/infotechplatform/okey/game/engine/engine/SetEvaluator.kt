package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.TileSet

class SetEvaluator {

    // globalConfig = tile

    fun isValid(tileSet: TileSet, currentOkey: NumberTile): Boolean {
        val numberTiles = ArrayList(tileSet.numberTiles)

        if (tileSet.jokerTiles.isNotEmpty()) {
            tileSet.jokerTiles.forEach { _ ->
                numberTiles.add(currentOkey)
            }
        }

        if (numberTiles.size < 3) {
            return false
        }

        if (isSameNumberSet(numberTiles)) {
            return true
        }

        return isSequenceSet(numberTiles)
    }

    private fun isSequenceSet(numberTiles: List<NumberTile>): Boolean {
        // TODO
        return false
    }

    private fun isSameNumberSet(numberTiles: List<NumberTile>): Boolean {
        if (numberTiles.size > 4 || numberTiles.size < 3) {
            return false
        }

        val tempTile = numberTiles[0]
        for (tile in numberTiles) {
            if (tile.number != tempTile.number) {
                return false
            }
        }

        val uniqueNumberTiles = numberTiles.toSet()
        if (uniqueNumberTiles.size != numberTiles.size) {
            return false
        }

        return true
    }

}