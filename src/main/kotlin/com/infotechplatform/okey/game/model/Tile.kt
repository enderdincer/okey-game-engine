package com.infotechplatform.okey.game.model


enum class TileColor(
        val colorId: Int
) {
    RED(0), GREEN(1), BLACK(2), YELLOW(3);
    companion object{
        fun fromId(id: Int): TileColor {
            return values().find { it.colorId == id }!!
        }
    }
}

enum class TileType{
    FALSE_JOKER, NUMBER_TILE
}

data class Tile(
        val number: Int? = null,
        val color: TileColor? = null,
        val tileType: TileType,
        var isJoker: Boolean = false
)


