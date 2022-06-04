package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.*

class RackEvaluator {


    // 4-5-6 + 2-3-4 + [rubbish....]

    // 10-11 4-5  8-9 2-3

    // 5-6-7-8-9
    private fun newEmptyRackMatrix(): MutableList<MutableList<Int>> {
        return mutableListOf(
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
                (1..13).map { 0 }.toMutableList(),
        )
    }


    fun zaxd(rack: Rack, currentOkey: Tile) {
        val rackMatrix = newEmptyRackMatrix()

        rack.numberTiles.forEach {
            rackMatrix[it.color.colorId][it.number - 1]++
        }

        for (i in 0..3) {
            println(rackMatrix[i])
        }

        val tileSets = mutableListOf<TileSet>()
        // 2. find consecutive 1s and 2s horizontally (first) and vertically

        for (i in 0..3) {
            var counter = 0
            for (j in 0..12) {
                val tileCount = rackMatrix[i][j]

                if (tileCount >= 1) {
                    counter++
                }

                if (tileCount == 0 && counter >= 3) {
                    val tileSet = TileSet(mutableListOf(), mutableListOf())
                    for (k in j - counter until j) {
                        rackMatrix[i][k]--
                        tileSet.numberTiles.add(NumberTile(k + 1, TileColor.fromId(i)))
                    }
                    tileSets.add(tileSet)
                    counter = 0
                }

                if (tileCount == 0) {
                    counter = 0
                }

            }
        }

        for (j in 0..12) {
            var counter = 0
            var indexes = mutableListOf<Int>()
            for (i in 0..3) {
                if (rackMatrix[i][j] >= 1) {
                    indexes.add(i)
                    counter++
                }
            }

            if (counter >= 3) {
                indexes.map { NumberTile(j + 1, TileColor.fromId(it)) }
                // set found
            }
        }

        for (i in 0..3) {
            println(rackMatrix[i])
        }

        println(rackMatrix)
    }

}