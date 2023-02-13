package com.infotechplatform.okey.game.engine.handler

import com.infotechplatform.okey.game.engine.GameEngine
import com.infotechplatform.okey.game.engine.model.*
import com.infotechplatform.okey.game.engine.utils.TestUtils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GameEngineTest {

    private val gameEngine = GameEngine(gameEventHandler = DefaultGameEventHandler())

    @Test
    fun gameEngineSimulation() {
        val preCreateState = GameState(gameId = "yeni oyun 123")
        val createGameEvent = GameEvent(type = GameEventType.CREATE_GAME, gameConfig = GameConfigs.getOkeyGameConfig())
        val createdState = gameEngine.getNextGameState(preCreateState, createGameEvent)

        Assertions.assertThat(createdState.centerTileStack).hasSize(106)

        val preAddPlayerState = createdState.copy()
        val addPlayerEvent = GameEvent(type = GameEventType.ADD_PLAYER, players = TestUtils.get4MockedPlayers())
        val playerAddedState = gameEngine.getNextGameState(preAddPlayerState, addPlayerEvent)

        Assertions.assertThat(playerAddedState.centerTileStack).hasSize(106)
        Assertions.assertThat(playerAddedState.players).hasSize(4)

        val preStartState = playerAddedState.copy()
        val startGameEvent = GameEvent(type = GameEventType.START_GAME)
        val startedGameState = gameEngine.getNextGameState(preStartState, startGameEvent)

        Assertions.assertThat(startedGameState.centerTileStack).hasSize(49)
        Assertions.assertThat(startedGameState.players).hasSize(4)
        Assertions.assertThat(startedGameState.players!!.all { it.index != null }).isTrue()
        Assertions.assertThat(startedGameState.players!![0].rack).hasSize(15)

        val selectedTile = startedGameState.players!![0].rack?.get(0)!!
        val preDiscardTileState = startedGameState.copy()
        val discardTileGameEvent = GameEvent(type = GameEventType.DISCARD_TILE, tile = selectedTile, players = listOf(Player(playerId = "Ender")))
        val afterDiscardTileState = gameEngine.getNextGameState(preDiscardTileState, discardTileGameEvent)

        Assertions.assertThat(afterDiscardTileState.centerTileStack).hasSize(49)
        Assertions.assertThat(afterDiscardTileState.players!!).hasSize(4)
        Assertions.assertThat(afterDiscardTileState.players!!.all { it.index != null }).isTrue()
        Assertions.assertThat(afterDiscardTileState.players!![0].rack).hasSize(14)

        println("======= END OF SIMULATION =======")
    }
}