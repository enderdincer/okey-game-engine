package com.enderdincer.okey.game.engine.utils

import com.enderdincer.okey.game.engine.model.Player
import java.io.File

object TestUtils {

    val TILES_NO_DUPLICATES_NO_JOKER_NO_FALSE_JOKER_FILE = File("src/test/resources/jsons/tiles_no_duplicate_no_false_joker.json")
    val TILES_NO_JOKER_NO_FALSE_JOKER_FILE = File("src/test/resources/jsons/tiles_no_false_joker.json")


    fun get4MockedPlayers(): MutableList<Player> = mutableListOf(
            Player(playerId = "Ender"),
            Player(playerId = "Taner"),
            Player(playerId = "Mustafa"),
            Player(playerId = "Zehra"),
    )
}