package com.enderdincer.okey.game.engine.evaluator.run

import com.enderdincer.okey.game.engine.domain.Tile

interface TileRunEvaluator {

    fun findAllRuns(rack: List<Tile>, joker: Tile): List<List<Tile>>
}