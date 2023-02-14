package com.enderdincer.okey.game.engine.model

object GameConfigs {

    fun getDefaultGameConfig(): GameConfig = GameConfig(
            numberOfPlayers = 4,
            numberOfFalseJokers = 2,
            numberOfTilesPerTileColor = 13,
            numberOfTilesInRack = 14
    )
}