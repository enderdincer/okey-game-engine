package com.enderdincer.okey.game.engine.domain

import com.enderdincer.okey.game.engine.config.GameConfig

data class GameState(
        val gameId: String? = null,
        val joker: Tile? = null,
        val players: List<Player>? = null,
        val centerTileStack: List<Tile>? = null,
        val gameConfig: GameConfig? = null,
        val isGameOverByPlayerWin: Boolean = false,
        val winningPlayer: Player? = null
)