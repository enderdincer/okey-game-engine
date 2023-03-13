package com.enderdincer.okey.game.engine.evaluator.set

import com.enderdincer.okey.game.engine.domain.Tile

interface TileSetEvaluator {

    fun findAllSets(rack: List<Tile>, joker: Tile): List<List<Tile>>
}