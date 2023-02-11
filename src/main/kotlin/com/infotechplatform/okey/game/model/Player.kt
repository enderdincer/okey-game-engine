package com.infotechplatform.okey.game.model

import com.infotechplatform.okey.game.experimental.strategy.GameStrategy

data class Player(
        val playerId: Int,
        var leftPlayer: Player? = null,
        var rightPlayer: Player? = null,
        var gameStrategy: GameStrategy? = null
) {

}