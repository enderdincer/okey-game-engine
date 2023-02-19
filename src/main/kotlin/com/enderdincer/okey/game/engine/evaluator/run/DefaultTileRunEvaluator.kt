package com.enderdincer.okey.game.engine.evaluator.run

import com.enderdincer.okey.game.engine.commons.TileHelper.hasDuplicate
import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor

class DefaultTileRunEvaluator : TileRunEvaluator {

    override fun findAllRuns(rack: List<Tile>, joker: Tile): List<List<Tile>> =
            TileColor.values()
                    .map { tileColor -> findAllRunsByColor(rack, joker, tileColor) }
                    .flatten()

    private fun findAllRunsByColor(rack: List<Tile>, joker: Tile, tileColor: TileColor): List<List<Tile>> {
        val allRuns = mutableListOf<List<Tile>>()
        val numberOfJokers = rack.count { it == joker }
        val sortedSameColoredTiles = rack.filter { it.color == tileColor }
                .sortedBy { it.number }

        val longRuns = findLongestRunsInSameColoredTiles(sortedSameColoredTiles, joker, numberOfJokers)
        allRuns.addAll(longRuns)

        val splitRuns = splitLongRunsWithDuplicateTiles(longRuns, rack)
        allRuns.addAll(splitRuns)

        val subRuns = findSubRuns(allRuns)
        allRuns.addAll(subRuns)

        return allRuns
    }

    private fun splitLongRunsWithDuplicateTiles(longestRuns: List<List<Tile>>, rack: List<Tile>): List<List<Tile>> {
        return longestRuns.mapNotNull { group ->
            if (group.size < 5)
                null
            else (2..group.size - 3).mapNotNull { index ->
                if (hasDuplicate(rack, group[index])) {
                    listOf(group.subList(0, index + 1), group.subList(index, group.size))
                } else null
            }.flatten()
        }.flatten()
    }

    private fun findSubRuns(runs: List<List<Tile>>): List<List<Tile>> =
            runs.asSequence()
                    .filter { it.size >= 4 }
                    .map { findSubRun(it) }
                    .flatten().toList()

    private fun findSubRun(run: List<Tile>): List<List<Tile>> {
        val subRuns = mutableListOf<List<Tile>>()

        (0 until run.size - 2).forEach { i ->
            (2 until run.size).forEach { j ->
                if (j > i && j - i >= 2) {
                    subRuns.add(run.subList(i, j + 1))
                }
            }
        }

        return subRuns
    }

    private fun findLongestRunsInSameColoredTiles(sortedSameColoredTiles: List<Tile>, joker: Tile, numberOfJokers: Int): List<List<Tile>> {
        val runs = mutableListOf<List<Tile>>()
        val mutableTiles = sortedSameColoredTiles.toMutableList()
        val tempRun = mutableListOf<Tile>()
        var numOfJokers = numberOfJokers
        val numberOfOnes = mutableTiles.count { it.number == 1 }

        var i = 0
        while (i < mutableTiles.size) {
            val currentTile = mutableTiles[i]
            if (tempRun.isEmpty()) {
                tempRun.add(currentTile)
                i++
                continue
            }
            val lastTileOfTempRun = tempRun[tempRun.size - 1]
            if ((lastTileOfTempRun == currentTile)
                    && i < mutableTiles.size - 1) {
                val removed: Tile = mutableTiles.removeAt(i)
                mutableTiles.add(removed)
                continue
            }
            if (lastTileOfTempRun.number!! + 1 == currentTile.number) {
                tempRun.add(currentTile)

                if (currentTile.number == 13 && tempRun.size >= 2) {
                    if(tempRun.size < 13 && numberOfOnes >= 1){
                        tempRun.add(mutableTiles[0])
                    }

                    if(tempRun.size == 13 && numberOfOnes == 2){
                        tempRun.add(mutableTiles[0])
                    }
                }
                i++
                continue
            }

            if ((lastTileOfTempRun.number!! + 2 == currentTile.number || lastTileOfTempRun.number == 12
                            && currentTile.number == 1)
                    && numOfJokers > 0) {
                tempRun.add(joker)
                tempRun.add(currentTile)
                numOfJokers--
                i++
                continue
            }
            if (tempRun.size >= 3) {
                runs.add(tempRun.toList())
            }
            tempRun.clear()
            tempRun.add(currentTile)
            i++
        }
        if (tempRun.size >= 3) {
            runs.add(tempRun.toList())
        }
        tempRun.clear()
        return runs
    }
}