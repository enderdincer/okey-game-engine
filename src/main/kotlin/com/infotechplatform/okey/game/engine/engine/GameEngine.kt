package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.engine.strategy.DummyGameStrategy
import com.infotechplatform.okey.game.engine.engine.strategy.SimpleSequenceOnlyNoOkeyGameStrategy
import com.infotechplatform.okey.game.engine.model.*
import java.util.*

class GameEngine(
        private val gameConfig: GameConfig,
        private val tileHandler: TileHandler
) {
    private val players: MutableList<Player> = mutableListOf()
    private val freeTiles: MutableList<Tile> = mutableListOf()
    private var okeyTile: NumberTile? = null
    private var isGameEnded: Boolean = false

    fun initGame() {
        initTiles()
        initPlayers()
    }

    fun play() {
        var roundNumber = 0
        while (!isGameEnded) {

            for (player in players) {
                if (isGameEnded) {
                    break
                }
                playTurn(player, roundNumber)
            }

            roundNumber++
        }
    }

    private fun playTurn(player: Player, roundNumber: Int) {
        if (roundNumber == 0 && player.playerId == 0) {

            if (player.gameStrategy!!.declareWin()) {
                endGame(player)
            }

            player.gameStrategy!!.discardTile()
            return
        } else {
            player.gameStrategy!!.drawTile(freeTiles)

            if (player.gameStrategy!!.declareWin()) {
                endGame(player)
            }

            player.gameStrategy!!.discardTile()
            return
        }
    }

    private fun endGame(winner: Player) {
        println(winner.rack)
        this.isGameEnded = true
    }

    private fun pickOkeyTile() {
        val randomTileIndex = Random().nextInt(104)
        okeyTile = freeTiles[randomTileIndex] as NumberTile
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
        freeTiles.shuffle()
    }

    private fun initPlayers() {
        (0 until gameConfig.numberOfPlayers).forEach {
            val player = Player(playerId = it, rack = Rack(mutableListOf(), mutableListOf()))
            if (player.playerId == 0) {
                player.gameStrategy = SimpleSequenceOnlyNoOkeyGameStrategy(tileHandler, RackProcessor(), player, okeyTile!!)
            } else {
                player.gameStrategy = DummyGameStrategy(tileHandler, player, currentOkey = okeyTile!!)
            }
            players.add(player)
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