package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.model.*
import com.enderdincer.okey.game.engine.utils.TestUtils
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DefaultGameEngineTest {

    private val gameEngine = GameEngines.defaultGameEngine()

    @Test
    fun gameEngineSimulation() {
        val preCreateState = GameState(gameId = "new game 123")
        val createGameEvent = GameEvent(type = GameEventType.CREATE_GAME, gameConfig = GameConfigs.getDefaultGameConfig())
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
        Assertions.assertThat(afterDiscardTileState.players!![0].discardTileStack).hasSize(1)

        val preDrawTileState = afterDiscardTileState.copy()
        val drawTileGameEvent = GameEvent(type = GameEventType.DRAW_TILE_FROM_CENTER_TILE_STACK, players = listOf(Player(playerId = "Taner")))
        val afterDrawTileState = gameEngine.getNextGameState(preDrawTileState, drawTileGameEvent)

        Assertions.assertThat(afterDrawTileState.centerTileStack).hasSize(48)
        Assertions.assertThat(afterDrawTileState.players!!).hasSize(4)
        Assertions.assertThat(afterDrawTileState.players!!.all { it.index != null }).isTrue()
        Assertions.assertThat(afterDrawTileState.players!![0].rack).hasSize(14)
        Assertions.assertThat(afterDrawTileState.players!![0].discardTileStack).hasSize(1)
        Assertions.assertThat(afterDrawTileState.players!![1].rack).hasSize(15)

        val selectedTile2 = startedGameState.players!![1].rack?.get(5)!!
        val preDiscardTileState2 = afterDrawTileState.copy()
        val discardTileEvent2 = GameEvent(type = GameEventType.DISCARD_TILE, tile = selectedTile2, players = listOf(Player(playerId = "Taner")))
        val discardedTileState2 = gameEngine.getNextGameState(preDiscardTileState2, discardTileEvent2)

        Assertions.assertThat(discardedTileState2.centerTileStack).hasSize(48)
        Assertions.assertThat(discardedTileState2.players!!).hasSize(4)
        Assertions.assertThat(discardedTileState2.players!!.all { it.index != null }).isTrue()
        Assertions.assertThat(discardedTileState2.players!![0].rack).hasSize(14)
        Assertions.assertThat(discardedTileState2.players!![0].discardTileStack).hasSize(1)
        Assertions.assertThat(discardedTileState2.players!![1].rack).hasSize(14)
        Assertions.assertThat(discardedTileState2.players!![1].discardTileStack).hasSize(1)

        val preDrawTileState2 = discardedTileState2.copy()
        val drawTileGameEvent2 = GameEvent(type = GameEventType.DRAW_TILE_FROM_DISCARD_TILE_STACK, players = listOf(Player(playerId = "Mustafa")))
        val drawnTileState2 = gameEngine.getNextGameState(preDrawTileState2, drawTileGameEvent2)

        Assertions.assertThat(drawnTileState2.centerTileStack).hasSize(48)
        Assertions.assertThat(drawnTileState2.players!!).hasSize(4)
        Assertions.assertThat(drawnTileState2.players!!.all { it.index != null }).isTrue()
        Assertions.assertThat(drawnTileState2.players!![0].rack).hasSize(14)
        Assertions.assertThat(drawnTileState2.players!![0].discardTileStack).hasSize(1)
        Assertions.assertThat(drawnTileState2.players!![1].rack).hasSize(14)
        Assertions.assertThat(drawnTileState2.players!![1].discardTileStack).hasSize(0)
        Assertions.assertThat(drawnTileState2.players!![2].rack).hasSize(15)
        Assertions.assertThat(drawnTileState2.players!![2].discardTileStack).hasSize(0)


        println("======= END OF SIMULATION =======")
    }
}