package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.JokerTile
import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

class TileHandler {

    fun drawFrom(player: Player, tileCollection: MutableList<Tile>, times: Int = 1) {
        (1..times).forEach { _ ->
            val drawnTile = tileCollection.last()
            tileCollection.removeLast()
            when (drawnTile) {
                is NumberTile -> player.rack.numberTiles.add(drawnTile)
                is JokerTile -> player.rack.jokerTiles.add(drawnTile)
                else -> throw Exception("Unknown tile type!")
            }
        }
    }

    fun throwPickedTileTo(player: Player, tile: Tile, tileCollection: MutableList<Tile>) {
        when (tile) {
            is NumberTile -> {
                val tileIndex = player.rack.numberTiles.indexOf(tile)
                val pickedTile = player.rack.numberTiles[tileIndex]
                player.rack.numberTiles.removeAt(tileIndex)
                tileCollection.add(pickedTile)
            }
            is JokerTile -> {
                val tileIndex = player.rack.jokerTiles.indexOf(tile)
                val pickedTile = player.rack.jokerTiles[tileIndex]
                player.rack.jokerTiles.removeAt(tileIndex)
                tileCollection.add(pickedTile)
            }
            else -> throw Exception("Unknown tile type!")
        }
    }
}