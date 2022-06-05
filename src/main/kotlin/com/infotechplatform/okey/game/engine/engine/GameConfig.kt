package com.infotechplatform.okey.game.engine.engine

data class GameConfig(
        val numberOfPlayers: Int,
        val numberOfTilesPerTileColor: Int,
        val numberOfJokers: Int,
        // todo new config: reshuffle tile stacks when free tiles finishes
)