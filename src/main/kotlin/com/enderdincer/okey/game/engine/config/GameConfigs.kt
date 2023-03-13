package com.enderdincer.okey.game.engine.config

object GameConfigs {

    @JvmStatic
    fun getDefaultGameConfig(): GameConfig = GameConfig(
            numberOfPlayers = 4,
            numberOfFalseJokers = 2,
            numberOfTilesPerTileColor = 13,
            numberOfTilesInRack = 14
    )
}