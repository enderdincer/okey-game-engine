package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.handler.DefaultGameEventHandler
import com.infotechplatform.okey.game.engine.model.GameConfigs
import com.infotechplatform.okey.game.engine.model.GameEvent
import com.infotechplatform.okey.game.engine.model.GameState
import org.junit.jupiter.api.Test

class DefaultGameEventHandlerTest {

    private val gameEventHandler = DefaultGameEventHandler()
    
    @Test
    fun testHandleStartGame(){

        val gameConfig = GameConfigs.getOkeyGameConfig()

        val prevGameState = GameState(
                players = mutableListOf(Play)
        )

        val gameEvent = GameEvent()

        val nextGameState = gameEventHandler.handleStartGame(prevGameState, gameEvent)
    }
}