package com.enderdincer.okey.game.engine.config

import com.enderdincer.okey.game.engine.domain.TileColor

data class GameConfig(
        val numberOfPlayers: Int,
        val numberOfTilesPerTileColor: Int,
        val numberOfFalseJokers: Int,
        val numberOfTilesInRack: Int,
) {
    fun getTotalNumberOfTiles(): Int = numberOfTilesPerTileColor * TileColor.values().size * 2 + numberOfFalseJokers
}