package com.enderdincer.okey.game.engine.domain

import com.enderdincer.okey.game.engine.config.GameConfig

data class GameEvent(
        val type: GameEventType,
        val players: List<Player>? = null,
        val tile: Tile? = null,
        val gameConfig: GameConfig? = null,
)

enum class GameEventType {
    CREATE_GAME,
    ADD_PLAYER,
    START_GAME,
    DRAW_TILE_FROM_CENTER_TILE_STACK,
    DRAW_TILE_FROM_DISCARD_TILE_STACK,
    DISCARD_TILE,
    DECLARE_WIN
}
