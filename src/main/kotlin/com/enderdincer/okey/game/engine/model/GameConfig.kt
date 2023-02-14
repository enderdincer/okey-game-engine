package com.enderdincer.okey.game.engine.model

data class GameConfig(
        val numberOfPlayers: Int,
        val numberOfTilesPerTileColor: Int,
        val numberOfFalseJokers: Int,
        val numberOfTilesInRack: Int,
        // todo new config: reshuffle tile stacks when free tiles finishes
) {
    fun getTotalNumberOfTiles(): Int = numberOfTilesPerTileColor * TileColor.values().size * 2 + numberOfFalseJokers
}