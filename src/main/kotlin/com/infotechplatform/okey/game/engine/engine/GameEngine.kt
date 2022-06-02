package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.*
import java.util.*

class GameEngine(
        private val gameConfig: GameConfig,
        private val tileHandler: TileHandler
) {
    private val players: MutableList<Player> = mutableListOf()
    private val freeTiles: MutableList<Tile> = mutableListOf()
    private var okeyTile: Tile? = null
    private var currentPlayer: Player? = null

    fun initGame() {
        initTiles()
        initPlayers()
        print("")
    }

    fun play(){
        // TODO
    }

    private fun pickOkeyTile() {
        val randomTileIndex = Random().nextInt(104)
        okeyTile = freeTiles[randomTileIndex]
    }

    private fun initTiles() {
        TileColor.values().forEach { tileColor ->
            (1..gameConfig.numberOfTilesPerTileColor).forEach { tileNumber ->
                freeTiles.add(NumberTile(tileNumber, tileColor))
                freeTiles.add(NumberTile(tileNumber, tileColor))
            }
        }

        (1..gameConfig.numberOfJokers).forEach { _ ->
            freeTiles.add(JokerTile())
        }

        pickOkeyTile()

        shuffleTiles()
    }

    private fun shuffleTiles() {
        // TODO
    }

    private fun initPlayers() {
        (0 until gameConfig.numberOfPlayers).forEach {
            players.add(Player(playerId = it, rack = Rack(mutableListOf(), mutableListOf())))
        }

        (0 until gameConfig.numberOfPlayers).forEach {
            players[it].rightPlayer = players[getRightPlayerId(it)]
            players[it].leftPlayer = players[getLeftPlayerId(it)]
        }

        players.forEach {
            if (it.playerId == 0) {
                tileHandler.drawFrom(it, freeTiles, 15)
            } else {
                tileHandler.drawFrom(it, freeTiles, 14)
            }
        }
    }

    private fun getRightPlayerId(playerId: Int): Int {
        return (playerId + 1) % gameConfig.numberOfPlayers
    }

    private fun getLeftPlayerId(playerId: Int): Int {
        val remainder = (playerId - 1) % gameConfig.numberOfPlayers
        return if (remainder < 0) {
            remainder + gameConfig.numberOfPlayers
        } else {
            remainder
        }
    }


}