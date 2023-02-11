package com.infotechplatform.okey.game.engine.model

data class GameEvent(
        val type: GameEventType,
        val players: List<Player>,
        val tile: Tile,
        val sourceTileCollection: List<Tile>,
        val targetTileCollection: List<Tile>,
        val gameConfig: GameConfig? = null,
)

enum class GameEventType {
    CREATE_GAME,
    ADD_PLAYER,
    START_GAME,
    DETERMINE_JOKER,
    DRAW_TILE,
    DISCARD_TILE,
    DECLARE_WIN
}
