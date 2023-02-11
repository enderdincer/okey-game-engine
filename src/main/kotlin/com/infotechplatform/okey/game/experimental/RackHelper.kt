package com.infotechplatform.okey.game.experimental

import com.infotechplatform.okey.game.engine.model.Tile
import com.infotechplatform.okey.game.engine.model.TileColor
import com.infotechplatform.okey.game.engine.model.TileType

class RackHelper {
//
//    fun getTileFromCoordinates(pair: Pair<Int, Int>): Tile {
//        return this.getTileFromCoordinates(pair.first, pair.second)
//    }
//
//    fun getTileFromCoordinates(i: Int, j: Int): Tile {
//        return Tile(number = j + 1, color = TileColor.fromId(i), tileType = TileType.NUMBER_TILE)
//    }
//
//    fun populateRackMatrix(rack: List<Tile>, joker: Tile): MutableList<MutableList<Int>> {
//        val rackMatrix = newEmptyRackMatrix()
//
//        rack.forEach {
//            rackMatrix[it.color!!.colorId][it.number!! - 1]++
//        }
//
//        rack.forEach {
//            rackMatrix[joker.color!!.colorId][joker.number!! - 1]++
//        }
//
//        return rackMatrix
//    }
//
//    fun printRackMatrix(rackMatrix: MutableList<MutableList<Int>>) {
//        for (i in 0..3) {
//            println(rackMatrix[i])
//        }
//    }
//
//    fun printRackAsMatrix(rack: List<Tile>, joker: Tile) {
//        val populatedRackMatrix = populateRackMatrix(rack, joker)
//
//        this.printRackMatrix(populatedRackMatrix)
//    }
//
//    private fun newEmptyRackMatrix(): MutableList<MutableList<Int>> {
//        return mutableListOf(
//                (1..13).map { 0 }.toMutableList(),
//                (1..13).map { 0 }.toMutableList(),
//                (1..13).map { 0 }.toMutableList(),
//                (1..13).map { 0 }.toMutableList(),
//        )
//    }
}