package com.infotechplatform.okey.game.model

data class GameEvent(
        val type: GameEventType,
        val player: Player,
        val sourceTileCollection: List<Tile>,
        val targetTileCollection: List<Tile>,
        val gameConfig: GameConfig,
)

enum class GameEventType{
    INIT_GAME, DRAW_TILE, DISCARD_TILE, DECLARE_WIN
}
