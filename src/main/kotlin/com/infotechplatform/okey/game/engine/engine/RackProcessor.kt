package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Rack
import com.infotechplatform.okey.game.engine.model.Tile
import com.infotechplatform.okey.game.engine.model.TileColor

class RackProcessor {

    fun getTileFromCoordinates(pair: Pair<Int, Int>): NumberTile {
        return this.getTileFromCoordinates(pair.first, pair.second)
    }

    fun getTileFromCoordinates(i: Int, j: Int): NumberTile {
        return NumberTile(number = j + 1, color = TileColor.fromId(i))
    }

    fun populateRackMatrix(rack: Rack, currentOkey: NumberTile): MutableList<MutableList<Int>> {
        val rackMatrix = newEmptyRackMatrix()

        rack.numberTiles.forEach {
            rackMatrix[it.color.colorId][it.number - 1]++
        }

        rack.jokerTiles.forEach {
            rackMatrix[currentOkey.color.colorId][currentOkey.number - 1]++
        }

        return rackMatrix
    }

    fun printRackMatrix(rackMatrix: MutableList<MutableList<Int>>) {
        for (i in 0..3) {
            println(rackMatrix[i])
        }
    }

    fun printRackAsMatrix(rack: Rack, currentOkey: NumberTile) {
        val populatedRackMatrix = populateRackMatrix(rack, currentOkey)

        this.printRackMatrix(populatedRackMatrix)
    }

    private fun newEmptyRackMatrix(): MutableList<MutableList<Int>> {
        return mutableListOf(
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
        )
    }
}