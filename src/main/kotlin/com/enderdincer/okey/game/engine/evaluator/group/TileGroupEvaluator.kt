package com.enderdincer.okey.game.engine.evaluator.group

import com.enderdincer.okey.game.engine.domain.Tile

interface TileGroupEvaluator {

    fun findAllGroups(rack: List<Tile>, joker: Tile): List<List<Tile>>
}