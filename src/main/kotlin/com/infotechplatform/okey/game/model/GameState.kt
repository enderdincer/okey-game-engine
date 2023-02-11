package com.infotechplatform.okey.game.model

data class GameState (
        val gameId: Int,
        val turnCount: Int = 0,
        val joker: Tile,
        val playerStates: Map<Int, PlayerState>,
        val centerTileStack: List<Tile>,
        val lastAction: GameEvent? = null
)

data class PlayerState (
        val playerId: Int,
        val rack: List<Tile>,
        val discardTileStack: List<Tile>
)