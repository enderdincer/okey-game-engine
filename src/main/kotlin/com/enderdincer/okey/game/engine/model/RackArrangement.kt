package com.enderdincer.okey.game.engine.model

data class RackArrangement(
        val unusedTiles: MutableList<Tile> = mutableListOf(),
        val sets: MutableList<List<Tile>> = mutableListOf()
){

    companion object {

        @JvmStatic
        fun of(rackArrangement: RackArrangement) = RackArrangement(
                unusedTiles = rackArrangement.unusedTiles.toMutableList(),
                sets = rackArrangement.sets.toMutableList()
        )
    }

    fun getTotalTileNumber() =
            sets.flatten().count() + unusedTiles.size

}

