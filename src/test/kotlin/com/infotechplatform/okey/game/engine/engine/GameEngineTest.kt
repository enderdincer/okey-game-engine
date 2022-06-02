package com.infotechplatform.okey.game.engine.engine

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GameEngineTest {

    private lateinit var gameEngine: GameEngine

    @BeforeEach
    fun setup() {
        gameEngine = GameEngine(
                GameConfig(
                        numberOfTilesPerTileColor = 13,
                        numberOfPlayers = 4,
                        numberOfJokers = 2
                ),
                TileHandler()
        )
    }

    @Test
    fun test() {
        gameEngine.initGame()
    }
}