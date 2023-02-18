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

        val splitRuns = splitLongRunsWithDuplicateTiles(longRuns, rack)

        val subRuns = findSubRuns()

        return allRuns
    }

    private fun splitLongRunsWithDuplicateTiles(longestRuns: List<List<Tile>>, rack: List<Tile>): List<List<Tile>> {
        return longestRuns.mapNotNull { group ->
            if (group.size < 5)
                null
            else (2..group.size - 3).mapNotNull { index ->
                    if (hasDuplicate(group, group[index])) {
                        listOf(group.subList(0, index), group.subList(index + 1, group.size - 1))
                    } else null
                }.flatten()
        }.flatten()
    }

    private fun findSubRuns(): List<List<Tile>> {
        return emptyList()
    }

    private fun findLongestRunsInSameColoredTiles(sortedSameColoredTiles: List<Tile>, joker: Tile, numberOfJokers: Int): List<List<Tile>> {
        val runs = mutableListOf<List<Tile>>()
        val mutableTiles = sortedSameColoredTiles.toMutableList()
        val tempRun = mutableListOf<Tile>()
        var numOfJokers = numberOfJokers

        var i = 0
        while (i < mutableTiles.size) {
            val currentTile: Tile = mutableTiles[i]
            if (tempRun.isEmpty()) {
                tempRun.add(currentTile)
                i++
                continue
            }
            val lastTileIntempRun: Tile = tempRun.get(tempRun.size - 1)
            if (lastTileIntempRun.equals(currentTile)
                    && i < mutableTiles.size - 1) {
                val removed: Tile = mutableTiles.removeAt(i)
                mutableTiles.add(removed)
                continue
            }
            if (lastTileIntempRun.number!! + 1 == currentTile.number || lastTileIntempRun.number == 13
                    && currentTile.number == 1) {
                if (tempRun.size >= 2) {
                    val tileBeforeTheLast: Tile = tempRun[tempRun.size - 2]
                    if (tileBeforeTheLast.number == 13) {
                        i++
                        continue
                    }
                }
                tempRun.add(currentTile)
                i++
                continue
            }
            if ((lastTileIntempRun.number!! + 2 == currentTile.number || lastTileIntempRun.number == 12
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
        return runs
    }
}