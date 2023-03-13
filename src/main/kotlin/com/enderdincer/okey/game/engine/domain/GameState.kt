package com.enderdincer.okey.game.engine.domain

import com.enderdincer.okey.game.engine.config.GameConfig

data class GameState(
        val gameId: String? = null,
        val turnCount: Int = 0,
        val joker: Tile? = null,
        val players: List<Player>? = null,
        val nextPlayerId: Int? = null,
        val centerTileStack: List<Tile>? = null,
        val gameConfig: GameConfig? = null,
        val lastAction: GameEvent? = null,
        val isGameOverByPlayerWin: Boolean = false,
        val winningPlayer: Player? = null
)