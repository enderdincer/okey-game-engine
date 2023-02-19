package com.enderdincer.okey.game.engine.commons

import com.enderdincer.okey.game.engine.model.Tile

object TileHelper {

    @JvmStatic
    fun addTiles(tiles: List<Tile>, vararg newTiles: Tile): List<Tile> {
        val mutableTileList = tiles.toMutableList()
        mutableTileList.addAll(newTiles)
        return mutableTileList
    }

    @JvmStatic
    fun hasDuplicate(tiles: List<Tile>, tile: Tile): Boolean {
        return tiles.count { it == tile } == 2
    }

    @JvmStatic
    fun removeTiles(tiles: List<Tile>, tilesToRemove: List<Tile>): List<Tile> {
        val newTiles = tiles.toMutableList()
        tilesToRemove.forEach { newTiles.remove(it) }
        return newTiles
    }

}