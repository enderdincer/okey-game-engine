package com.infotechplatform.okey.game.engine.engine.strategy

import com.fasterxml.jackson.databind.ObjectMapper
import com.infotechplatform.okey.game.engine.engine.RackHelper
import com.infotechplatform.okey.game.engine.engine.TileHandler
import com.infotechplatform.okey.game.engine.model.JokerTile
import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

class SimpleSequenceOnlyNoOkeyGameStrategy(
        private val tileHandler: TileHandler,
        private val rackHelper: RackHelper,
        override val player: Player,
        override val currentOkey: NumberTile
) : AbstractGameStrategy(player, currentOkey) {

    private var rackMatrix: MutableList<MutableList<Int>> = mutableListOf()
    private var currentSets = mutableListOf(mutableListOf<NumberTile>())

    override fun drawTile(freeTiles: MutableList<Tile>) {
        updateRackMatrix()
        removeValidSetsFromRackMatrix(3)
        val remainingNumOfTilesBeforeAdding = findRemainingNumOfTiles()

        updateRackMatrix()
        addTileToRackMatrixFromPlayerStack()
        removeValidSetsFromRackMatrix(3)
        val remainingNumOfTilesAfterAdding = findRemainingNumOfTiles()

        if (remainingNumOfTilesAfterAdding <= remainingNumOfTilesBeforeAdding) {
            tileHandler.drawFrom(player, player.leftPlayer!!.tileStack)
        } else {
            tileHandler.drawFrom(player, freeTiles)
        }
    }

    override fun declareWin(): Boolean {
        updateRackMatrix()
        removeValidSetsFromRackMatrix(3)
        val remainingNumOfTiles = findRemainingNumOfTiles()

        if(remainingNumOfTiles == 1){
            println(ObjectMapper().writeValueAsString(currentSets))
            return true
        }

        return false
    }

    override fun discardTile() {
        updateRackMatrix()
        removeValidSetsFromRackMatrix(3)
        val tile = pickTileToDiscard()
        tileHandler.throwPickedTileTo(player, tile, player.tileStack)
    }

    private fun updateRackMatrix() {
        this.rackMatrix = rackHelper.populateRackMatrix(this.player.rack, this.currentOkey)
    }

    private fun removeValidSetsFromRackMatrix(minSetThreshold: Int) {
        currentSets = mutableListOf()
        for (i in 0..3) {
            var counter = 0
            for (j in 0..12) {
                val tileCount = rackMatrix[i][j]

                if (tileCount >= 1) {
                    counter++
                }

                if (tileCount == 0 && counter >= minSetThreshold) {
                    val set = mutableListOf<NumberTile>()
                    for (k in j - counter until j) {
                        rackMatrix[i][k]--
                        set.add(rackHelper.getTileFromCoordinates(i,k))
                    }
                    currentSets.add(set)
                    counter = 0
                }

                if (tileCount == 0) {
                    counter = 0
                }
            }
        }
    }

    private fun findRemainingNumOfTiles(): Int {
        var sum = 0

        for (i in 0..3) {
            for (j in 0..12) {
                sum += this.rackMatrix[i][j]
            }
        }

        return sum
    }

    private fun addTileToRackMatrixFromPlayerStack() {
        val tile = player.leftPlayer!!.tileStack.last()

        when (tile) {
            is NumberTile -> {
                rackMatrix[tile.color.colorId][tile.number - 1]++
            }
            is JokerTile -> {
                rackMatrix[currentOkey.color.colorId][currentOkey.number - 1]++
            }
            else -> throw Exception()
        }
    }

    private fun pickTileToDiscard(): Tile {
        updateRackMatrix()
        removeValidSetsFromRackMatrix(2)

        if (findRemainingNumOfTiles() != 0) {
            return pickTile()
        }

        updateRackMatrix()
        removeValidSetsFromRackMatrix(3)

        if (findRemainingNumOfTiles() != 0) {
            return pickTile()
        }

        updateRackMatrix()
        return pickTile()
    }

    private fun pickTileCoordinatesFromRemaining(): Pair<Int, Int> {
        for (i in 0 until rackMatrix.size) {
            for (j in 0 until rackMatrix[0].size) {
                if (rackMatrix[i][j] > 0) {
                    return Pair(i, j)
                }
            }
        }
        throw Exception()
    }

    private fun pickTile(): Tile {
        val coordinates = pickTileCoordinatesFromRemaining()
        val pickedTile = rackHelper.getTileFromCoordinates(coordinates)
        return if (player.rack.numberTiles.contains(pickedTile)) {
            pickedTile
        } else {
            JokerTile()
        }
    }
}