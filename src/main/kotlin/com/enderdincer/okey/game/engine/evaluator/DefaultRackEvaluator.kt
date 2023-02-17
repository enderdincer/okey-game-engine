package com.enderdincer.okey.game.engine.evaluator

import com.enderdincer.okey.game.engine.model.GameConfig
import com.enderdincer.okey.game.engine.model.RackArrangement
import com.enderdincer.okey.game.engine.model.RackEvalResult
import com.enderdincer.okey.game.engine.model.Tile

class DefaultRackEvaluator(
        override val gameConfig: GameConfig
) : BaseRackEvaluator(gameConfig) {

    override fun evaluate(rack: List<Tile>, joker: Tile): RackEvalResult {
        val allPossibleRackArrangements = findAllPossibleRackArrangements(rack, joker)
        return RackEvalResult(bestArrangementByRemainingTiles = RackArrangement(listOf(), listOf()))
//        return RackEvalResult(
//                isWinning = isWinning(allPossibleRackArrangements),
//                allPossibleRackArrangements = allPossibleRackArrangements,
//                bestArrangementByRemainingTiles = bestArrangementByRemainingTiles(allPossibleRackArrangements)
//        )
    }

    private fun findAllPossibleRackArrangements(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val allArrangements = mutableListOf<List<Tile>>()
        allArrangements.addAll(findAllGroups(rack, joker))
//        allArrangements.addAll(findAllRuns(rack, joker))
        return allArrangements
    }

    private fun findAllGroups(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val numberOfJokers = rack.count { it == joker }

        if (numberOfJokers == 0)
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 3)

        if (numberOfJokers == 1) {
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 2)
                    .asSequence()
                    .map { group ->
                        when (group.size) {
                            2 -> listOf(addTiles(group, joker))
                            3 -> listOf(group, addTiles(group, joker))
                            else -> listOf()
                        }
                    }.flatten().toList()
        }

        if (numberOfJokers == 2) {
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 1)
                    .asSequence()
                    .map { group ->
                        when (group.size) {
                            1 -> listOf(addTiles(group, joker, joker))
                            2 -> listOf(addTiles(group, joker), addTiles(group, joker, joker))
                            3 -> listOf(group, addTiles(group, joker))
                            else -> listOf()
                        }
                    }.flatten().toList()
        }

        return emptyList()
    }

    private fun addTiles(tiles: List<Tile>, vararg newTiles: Tile): List<Tile> {
        val mutableTileList = tiles.toMutableList()
        mutableTileList.addAll(newTiles)
        return mutableTileList
    }

    private fun findAllGroupsWithoutJokerByMinSize(rack: List<Tile>, joker: Tile, minSize: Int): List<List<Tile>> {
        val allGroupsByMinSize = mutableListOf<List<Tile>>()
        (1 until gameConfig.numberOfTilesPerTileColor).forEach { index ->
            val group = rack.asSequence().distinct()
                    .filter { tile -> tile.number == index }
                    .filter { tile -> tile != joker }.toList()
            if (group.size >= minSize) {
                allGroupsByMinSize.add(group)
                if (group.size == 4) {
                    group.forEach { t ->
                        allGroupsByMinSize.add(group.filter { tile -> tile != t })
                    }
                }
            }
        }
        return allGroupsByMinSize
    }

    private fun findAllRuns(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        return listOf()
    }

    private fun bestArrangementByRemainingTiles(allPossibleRackArrangements: List<RackArrangement>): RackArrangement {
        return RackArrangement(listOf(), listOf())
    }

    private fun isWinning(allPossibleRackArrangements: List<RackArrangement>): Boolean {
        return false
    }

    private fun toArrangement(tiles: List<Tile>): RackArrangement {
        return RackArrangement(listOf(), listOf())
    }
}