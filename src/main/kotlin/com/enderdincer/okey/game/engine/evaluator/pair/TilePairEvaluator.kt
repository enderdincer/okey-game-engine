package com.enderdincer.okey.game.engine.evaluator.pair

import com.enderdincer.okey.game.engine.domain.Tile

interface TilePairEvaluator {

    fun findAllPairs(rack: List<Tile>, joker: Tile): List<List<Tile>>
}