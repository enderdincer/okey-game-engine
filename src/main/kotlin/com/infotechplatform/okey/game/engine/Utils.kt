package com.infotechplatform.okey.game.engine

import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.PlayerState

object Utils {

    fun toPlayerState(players: List<Player>) = players.associate { it.playerId to PlayerState(it.playerId) }
}