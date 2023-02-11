package com.infotechplatform.okey.game.experimental

//import com.infotechplatform.okey.game.engine.engine.strategy.DummyGameStrategy
//import com.infotechplatform.okey.game.engine.engine.strategy.SimpleCompleteGameStrategy
//import com.infotechplatform.okey.game.engine.engine.strategy.SimpleSequenceOnlyNoOkeyGameStrategy
//import com.infotechplatform.okey.game.engine.model.*
//import java.util.*

/**
 *
 * https://www.pagat.com/rummy/okey.html
 */

class GameEngine(
//        private val gameConfig: GameConfig,
//        private val tileHandler: TileHandler
) {
//    private val players: MutableList<Player> = mutableListOf()
//    private val centerTileStack: MutableList<Tile> = mutableListOf()
//    private var joker: Tile? = null
//    private var isGameEnded: Boolean = false
//
//    fun initGame() {
//        initTiles()
//        initPlayers()
//    }
//
//    fun play() {
//        var roundNumber = 0
//        while (!isGameEnded) {
//
//            for (player in players) {
//                if (isGameEnded) {
//                    break
//                }
//                playTurn(player, roundNumber)
//            }
//
//            roundNumber++
//        }
//    }
//
//    private fun playTurn(player: Player, roundNumber: Int) {
//        if (roundNumber == 0 && player.playerId == 0) {
//
//            if (player.gameStrategy!!.declareWin()) {
//                endGame(player)
//            }
//
//            player.gameStrategy!!.discardTile()
//            return
//        } else {
//            player.gameStrategy!!.drawTile(centerTileStack)
//
//            if (player.gameStrategy!!.declareWin()) {
//                endGame(player)
//            }
//
//            player.gameStrategy!!.discardTile()
//            return
//        }
//    }
//
//    private fun endGame(winner: Player) {
//        println(winner.rack)
//        this.isGameEnded = true
//    }
//
//    private fun pickJoker() {
//        val randomTileIndex = Random().nextInt(gameConfig.getTotalNumberOfTiles())
//        joker = centerTileStack[randomTileIndex]
//    }
//
//    private fun initTiles() {
//        TileColor.values().forEach { tileColor ->
//            (1..gameConfig.numberOfTilesPerTileColor).forEach { tileNumber ->
//                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
//                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
//            }
//        }
//
//        (1..gameConfig.numberOfFalseJokers).forEach { _ ->
//            centerTileStack.add(Tile(tileType = TileType.FALSE_JOKER))
//        }
//
//        pickJoker()
//
//        shuffleTiles()
//    }
//
//    private fun shuffleTiles() {
//        centerTileStack.shuffle()
//    }
//
//    private fun initPlayers() {
//        (0 until gameConfig.numberOfPlayers).forEach {
//            val player = Player(playerId = it, gameStrategy = SimpleCompleteGameStrategy(tileHandler, rackHelper = RackHelper()))
//            if (player.playerId == 0) {
//                player.gameStrategy = SimpleSequenceOnlyNoOkeyGameStrategy(tileHandler, RackHelper(), player, joker!!)
//            } else {
//                player.gameStrategy = DummyGameStrategy(tileHandler, player, joker = joker!!)
//            }
//            players.add(player)
//        }
//
//        (0 until gameConfig.numberOfPlayers).forEach {
//            players[it].rightPlayer = players[getRightPlayerId(it)]
//            players[it].leftPlayer = players[getLeftPlayerId(it)]
//        }
//
//        players.forEach {
//            if (it.playerId == 0) {
//                tileHandler.draw(it, centerTileStack, 15)
//            } else {
//                tileHandler.draw(it, centerTileStack, 14)
//            }
//        }
//    }
//
//    private fun getRightPlayerId(playerId: Int): Int {
//        return (playerId + 1) % gameConfig.numberOfPlayers
//    }
//
//    private fun getLeftPlayerId(playerId: Int): Int {
//        val remainder = (playerId - 1) % gameConfig.numberOfPlayers
//        return if (remainder < 0) {
//            remainder + gameConfig.numberOfPlayers
//        } else {
//            remainder
//        }
//    }


}