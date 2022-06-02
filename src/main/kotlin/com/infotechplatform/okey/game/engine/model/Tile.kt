package com.infotechplatform.okey.game.engine.model


enum class TileColor {
    RED, GREEN, BLACK, YELLOW
}

interface Tile

data class NumberTile(
        val number: Int,
        val color: TileColor,
) : Tile

class JokerTile: Tile
