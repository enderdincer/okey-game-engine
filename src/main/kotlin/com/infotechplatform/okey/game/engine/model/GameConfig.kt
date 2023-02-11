package com.infotechplatform.okey.game.engine.model

import com.infotechplatform.okey.game.engine.model.TileColor

data class GameConfig(
        val numberOfPlayers: Int,
        val numberOfTilesPerTileColor: Int,
        val numberOfFalseJokers: Int,
        // todo new config: reshuffle tile stacks when free tiles finishes
) {
    fun getTotalNumberOfTiles(): Int = numberOfTilesPerTileColor * TileColor.values().size + numberOfFalseJokers
}