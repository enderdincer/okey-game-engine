package com.infotechplatform.okey.game.engine

import com.infotechplatform.okey.game.engine.engine.GameConfig
import com.infotechplatform.okey.game.engine.engine.GameEngine
import com.infotechplatform.okey.game.engine.engine.TileHandler


fun main() {
    val tileHandler = TileHandler()
    val gameConfig = GameConfig(4, 13, 2)
    val gameEngine = GameEngine(gameConfig, tileHandler)

    gameEngine.initGame()
    gameEngine.play()
}

// 14 +1
// 4-4-3-3 +1
// 5-3-3-3 +1
// 5-5-4 +1

// 4-4-4


// PART 0
// GAME SETUP

// PART 1
// DETERMINE IF A HAND IS WINNING

// PART 1.1
// DETERMINE GROUP OF TILES ARE VALID SETS

// PART 2
// PLAY TO WIN

// ===== PLAY STEPS ===== //

// DECIDE ON WHAT TO THROW
// THROW
// HAVE 14 TILES

// DECIDE WETHER DRAW FROM TILE STACK OR FREE TILES
// HAVE 15 TILES

// PROCESS
// IS WIN?

// DECIDE ON WHAT TO THROW
// THROW
// HAVE 14 TILES