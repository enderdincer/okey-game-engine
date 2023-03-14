package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.config.GameConfig
import com.enderdincer.okey.game.engine.domain.Player
import com.enderdincer.okey.game.engine.domain.Tile
import java.util.*

abstract class AbstractGameEventHandler : GameEventHandler {

    protected fun findLeftPlayer(players: List<Player>, playerIndex: Int, numberOfPlayers: Int): Player? =
            players.find { it.index == getLeftPlayerIndex(playerIndex, numberOfPlayers) }

    protected fun findRightPlayer(players: List<Player>, playerIndex: Int, numberOfPlayers: Int): Player? =
            players.find { it.index == getRightPlayerIndex(playerIndex, numberOfPlayers) }

    protected fun determineJoker(gameConfig: GameConfig, centerTileStack: MutableList<Tile>): Tile {
        val randomTileIndex = Random().nextInt(gameConfig.getTotalNumberOfTiles())
        return centerTileStack[randomTileIndex]
    }

    protected fun getUpdatedPlayers(players: List<Player>, updatedPlayer: Player): List<Player> =
            players.map { if (it.playerId == updatedPlayer.playerId) updatedPlayer else it }

    private fun getRightPlayerIndex(playerIndex: Int, numberOfPlayers: Int): Int {
        return (playerIndex + 1) % numberOfPlayers
    }

    private fun getLeftPlayerIndex(playerIndex: Int, numberOfPlayers: Int): Int {
        val remainder = (playerIndex - 1) % numberOfPlayers
        return if (remainder < 0) {
            remainder + numberOfPlayers
        } else {
            remainder
        }
    }
}