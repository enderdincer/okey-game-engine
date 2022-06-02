package com.infotechplatform.okey.game.engine.model

data class Rack(
        val numberTiles: MutableList<NumberTile>,
        val jokerTiles: MutableList<JokerTile>,
)