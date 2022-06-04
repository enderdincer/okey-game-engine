package com.infotechplatform.okey.game.engine.model


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

interface Tile

data class NumberTile(
        val number: Int,
        val color: TileColor,
) : Tile

class JokerTile : Tile
