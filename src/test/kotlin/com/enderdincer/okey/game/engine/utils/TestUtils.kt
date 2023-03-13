package com.enderdincer.okey.game.engine.utils

import com.enderdincer.okey.game.engine.domain.Player

object TestUtils {

    fun get4TestPlayers(): MutableList<Player> = mutableListOf(
            Player(playerId = "Player 1"),
            Player(playerId = "Player 2"),
            Player(playerId = "Player 3"),
            Player(playerId = "Player 4"),
    )
}