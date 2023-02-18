package com.enderdincer.okey.game.engine.evaluator.group

import com.enderdincer.okey.game.engine.commons.TileHelper
import com.enderdincer.okey.game.engine.commons.ifTrue
import com.enderdincer.okey.game.engine.model.GameConfig
import com.enderdincer.okey.game.engine.model.Tile

class DefaultTileGroupEvaluator(private val gameConfig: GameConfig): TileGroupEvaluator {

    override fun findAllGroups(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val numberOfJokers = rack.count { it == joker }

        if (numberOfJokers == 0)
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 3)

        if (numberOfJokers == 1) {
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 2)
                    .asSequence()
                    .map { group ->
                        when (group.size) {
                            2 -> listOf(TileHelper.addTiles(group, joker))
                            3 -> listOf(group, TileHelper.addTiles(group, joker))
                            else -> listOf()
                        }
                    }.flatten().toList()
        }

        if (numberOfJokers == 2) {
            return findAllGroupsWithoutJokerByMinSize(rack, joker, 1)
                    .asSequence()
                    .map { group ->
                        when (group.size) {
                            1 -> listOf(TileHelper.addTiles(group, joker, joker))
                            2 -> listOf(TileHelper.addTiles(group, joker), TileHelper.addTiles(group, joker, joker))
                            3 -> listOf(group, TileHelper.addTiles(group, joker))
                            else -> listOf()
                        }
                    }.flatten().toList()
        }

        return emptyList()
    }

    private fun findAllGroupsWithoutJokerByMinSize(rack: List<Tile>, joker: Tile, minSize: Int): List<List<Tile>> {
        val allGroupsByMinSize = mutableListOf<List<Tile>>()
        (1 until gameConfig.numberOfTilesPerTileColor).forEach { index ->
            val group = rack.asSequence().distinct()
                    .filter { tile -> tile.number == index }
                    .filter { tile -> tile != joker }.toList()
            if (group.size >= minSize) {
                allGroupsByMinSize.add(group)
                (group.size == 4).ifTrue {
                    group.forEach { t ->
                        allGroupsByMinSize.add(group.filter { tile -> tile != t })
                    }
                }
            }
        }
        return allGroupsByMinSize
    }

}