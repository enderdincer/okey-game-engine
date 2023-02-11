package com.infotechplatform.okey.game.engine.model

data class Player(
        val playerId: String,
        val rack: MutableList<Tile>? = null,
        val discardTileStack: MutableList<Tile>? = null,
        var leftPlayer: Player? = null,
        var rightPlayer: Player? = null,
)