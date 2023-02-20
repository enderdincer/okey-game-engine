package com.enderdincer.okey.game.engine.evaluator.pair

import com.enderdincer.okey.game.engine.model.Tile

class DefaultTilePairEvaluator : TilePairEvaluator {

    override fun findAllPairs(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val pairs = mutableListOf<List<Tile>>()
        val numberOfJokers = rack.count { it == joker }
        val arrangement = rack.filter { it == joker }.groupBy { it.toString() }.values
        pairs.addAll(arrangement.filter { it.size == 2 })

        // TODO FIND THIS AND ADD JOKERS
        val unusedTiles = listOf<Tile>()

        if (numberOfJokers == 2) {

            return pairs
        }

        if (numberOfJokers == 1) {

            return pairs
        }

        return pairs
    }
}