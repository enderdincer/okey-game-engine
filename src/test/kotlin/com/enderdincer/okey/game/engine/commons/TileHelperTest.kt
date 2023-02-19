package com.enderdincer.okey.game.engine.commons

import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TileHelperTest {


    @Test
    fun removeTilesTest(){
        val tiles = listOf(
                Tile(1, TileColor.RED),
                Tile(2, TileColor.RED),
                Tile(2, TileColor.RED),
                Tile(3, TileColor.RED),
                Tile(4, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(6, TileColor.RED),
                Tile(7, TileColor.RED),
        )
        val tilesToRomve = listOf(
                Tile(1, TileColor.RED),
                Tile(2, TileColor.RED),
                Tile(3, TileColor.RED),
        )

        val actualNewTiles = TileHelper.removeTiles(tiles, tilesToRomve)

        val expectedNewTiles = listOf(
                Tile(2, TileColor.RED),
                Tile(4, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(5, TileColor.RED),
                Tile(6, TileColor.RED),
                Tile(7, TileColor.RED),
        )

        Assertions.assertThat(actualNewTiles).isEqualTo(expectedNewTiles)
    }
}