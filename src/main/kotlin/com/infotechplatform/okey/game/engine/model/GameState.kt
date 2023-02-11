package com.infotechplatform.okey.game.engine.model

data class GameState(
        val gameId: String? = null,
        val turnCount: Int = 0,
        val joker: Tile? = null,
        val players: List<Player>? = null,
        val nextPlayerId: Int? = null,
        val centerTileStack: List<Tile>? = null,
        val gameConfig: GameConfig? = null,
        val lastAction: GameEvent? = null
)

data class PlayerState(
        val playerId: String,
        val rack: List<Tile>? = null,
        val discardTileStack: List<Tile>? = null
)