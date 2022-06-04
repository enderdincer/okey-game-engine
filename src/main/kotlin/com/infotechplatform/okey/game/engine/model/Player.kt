package com.infotechplatform.okey.game.engine.model

import com.infotechplatform.okey.game.engine.engine.strategy.GameStrategy

data class Player(
        val playerId: Int,
        val rack: Rack,
        val tileStack: MutableList<Tile> = mutableListOf(),
        var leftPlayer: Player? = null,
        var rightPlayer: Player? = null,
        var gameStrategy: GameStrategy? = null
){
    override fun toString(): String {
        return "Player(playerId=${playerId}, rack=${rack}, tileStack=${tileStack}, leftPlayerId=${leftPlayer?.playerId}, rightPlayerId=${rightPlayer?.playerId}, gameStrategy=${gameStrategy})"
    }
}