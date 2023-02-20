package com.enderdincer.okey.game.engine.evaluator.pair

import com.enderdincer.okey.game.engine.model.Tile

class DefaultTilePairEvaluator : TilePairEvaluator {

    override fun findAllPairs(rack: List<Tile>, joker: Tile): List<List<Tile>> {
        val pairs = mutableListOf<List<Tile>>()
        val numberOfJokers = rack.count { it == joker }
        val arrangement = rack.filter { it != joker }.groupBy { it.toString() }.values
        pairs.addAll(arrangement.filter { it.size == 2 })

        val singleTiles = arrangement.filter { it.size == 1 }.flatten()

        if (numberOfJokers == 2) {
            if (singleTiles.size == 1) {
                pairs.add(listOf(joker, singleTiles[0]))
                pairs.add(listOf(joker))
                return pairs
            }

            if (singleTiles.size > 1) {
                singleTiles.forEachIndexed { index, tile ->
                    if (index < 2)
                        pairs.add(listOf(joker, tile))
                    else
                        pairs.add(listOf(tile))
                }
                return pairs
            }
        }

        if (numberOfJokers == 1) {
            if (singleTiles.isEmpty()) {
                pairs.add(listOf(joker))
                return pairs
            }

            if (singleTiles.size > 1) {
                pairs.add(listOf(joker, singleTiles[0]))
                pairs.addAll((1 until singleTiles.size).map { listOf(singleTiles[it]) })
                return pairs
            }
        }

        pairs.addAll(singleTiles.map { listOf(it) })
        return pairs
    }
}