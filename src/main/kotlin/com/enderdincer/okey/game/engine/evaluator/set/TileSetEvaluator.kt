package com.enderdincer.okey.game.engine.evaluator.set

import com.enderdincer.okey.game.engine.model.Tile

interface TileSetEvaluator {

    fun findAllSets(rack: List<Tile>, joker: Tile): List<List<Tile>>

//    fun getSetType(set: List<Tile>)
}