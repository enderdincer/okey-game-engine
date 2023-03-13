package com.enderdincer.okey.game.engine.domain

data class RackArrangement(
        val unusedTiles: MutableList<Tile> = mutableListOf(),
        val sets: MutableList<List<Tile>> = mutableListOf(),
        val pairs: List<List<Tile>> = listOf()
) {

    companion object {

        @JvmStatic
        fun of(rackArrangement: RackArrangement) = RackArrangement(
                unusedTiles = rackArrangement.unusedTiles.toMutableList(),
                sets = rackArrangement.sets.toMutableList()
        )
    }

    fun getTotalTileNumber() =
            sets.flatten().count() + unusedTiles.size + pairs.flatten().count()

}

