package com.infotechplatform.okey.game.engine.model

data class Player(
        val playerId: Int,
        val rack: Rack,
        var leftPlayer: Player? = null,
        var rightPlayer: Player? = null,
        val tileStack: MutableList<Tile> = mutableListOf()
)