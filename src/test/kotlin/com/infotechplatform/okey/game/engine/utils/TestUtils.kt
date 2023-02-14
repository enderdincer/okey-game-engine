package com.infotechplatform.okey.game.engine.utils

import com.enderdincer.okey.game.engine.model.Player

object TestUtils {

    fun get4MockedPlayers(): MutableList<Player> = mutableListOf(
            Player(playerId = "Ender"),
            Player(playerId = "Taner"),
            Player(playerId = "Mustafa"),
            Player(playerId = "Zehra"),
    )
}