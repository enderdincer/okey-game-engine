package com.infotechplatform.okey.game.engine.model


enum class TileColor {
    RED, GREEN, BLACK, YELLOW
}

interface Tile

data class NumericTile(
        val color: TileColor,
        val number: Int,
) : Tile

class JokerTile: Tile

// todo
data class Hand(
        val numericTiles: List<NumericTile>,
        val jokerTiles: List<JokerTile>,
)


fun main() {
    val tiles = listOf<Tile>(NumericTile(TileColor.BLACK, 12), JokerTile())

    val tile = tiles[0]

    (tile as NumericTile).color
}
