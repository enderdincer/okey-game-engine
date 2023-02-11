package com.infotechplatform.okey.game.experimental

import com.infotechplatform.okey.game.engine.model.Tile

class SetEvaluator {

//    // globalConfig = tile
//
//    fun isValid(tileSet: TileSet, joker: Tile): Boolean {
//        val numberTiles = tileSet.numberTiles.toMutableList()
//
//        if (tileSet.jokerTiles.isNotEmpty()) {
//            tileSet.jokerTiles.forEach { _ ->
//                numberTiles.add(joker)
//            }
//        }
//
//        if (numberTiles.size < 3) {
//            return false
//        }
//
//        if (isSameNumberSet(numberTiles)) {
//            return true
//        }
//
//        return isSequenceSet(numberTiles)
//    }
//
//    private fun isSequenceSet(numberTiles: List<Tile>): Boolean {
//        // TODO
//        return false
//    }
//
//    private fun isSameNumberSet(numberTiles: List<Tile>): Boolean {
//        if (numberTiles.size > 4 || numberTiles.size < 3) {
//            return false
//        }
//
//        val tempTile = numberTiles[0]
//        for (tile in numberTiles) {
//            if (tile.number != tempTile.number) {
//                return false
//            }
//        }
//
//        val uniqueNumberTiles = numberTiles.toSet()
//        if (uniqueNumberTiles.size != numberTiles.size) {
//            return false
//        }
//
//        return true
//    }

}