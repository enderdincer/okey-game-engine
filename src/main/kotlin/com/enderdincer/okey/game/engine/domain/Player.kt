package com.enderdincer.okey.game.engine.domain

data class Player(
        val playerId: String,
        var rack: MutableList<Tile>? = null,
        var discardTileStack: MutableList<Tile>? = null,
        var leftPlayer: Player? = null,
        var rightPlayer: Player? = null,
        var index: Int? = null
){

    fun draw(sourceTileCollection: MutableList<Tile>, times: Int = 1) {
        (1..times).forEach { _ ->
            val drawnTile = sourceTileCollection.removeLast()
            rack!!.add(drawnTile)
        }
    }

    fun discardTile(selectedTile: Tile) {
        val tileIndex = rack!!.indexOf(selectedTile)
        val matchingTileInRack = rack!![tileIndex]
        rack!!.removeAt(tileIndex)
        discardTileStack!!.add(matchingTileInRack)
    }

    override fun toString(): String {
        return ""
    }
}