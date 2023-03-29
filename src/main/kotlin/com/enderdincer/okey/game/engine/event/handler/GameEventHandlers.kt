package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.GameEventType
import com.enderdincer.okey.game.engine.event.validator.GameEventValidators

object GameEventHandlers {

    @JvmStatic
    fun getGameEventHandler(gameEventType: GameEventType): GameEventHandler {
        val gameEventValidator = GameEventValidators.getDefaultGameEventValidator()

        return when (gameEventType) {
            GameEventType.CREATE_GAME -> CreateGameEventHandler(gameEventValidator)
            GameEventType.ADD_PLAYER -> AddPlayerGameEventHandler(gameEventValidator)
            GameEventType.START_GAME -> StartGameEventHandler(gameEventValidator)
            GameEventType.DRAW_TILE_FROM_CENTER_TILE_STACK -> DrawFromCenterTileStackGameEventHandler(gameEventValidator)
            GameEventType.DRAW_TILE_FROM_DISCARD_TILE_STACK -> DrawFromDiscardTileStackGameEventHandler(gameEventValidator)
            GameEventType.DISCARD_TILE -> DiscardTileGameEventHandler(gameEventValidator)
            GameEventType.DECLARE_WIN -> DeclareWinGameEventHandler(gameEventValidator)
        }
    }
}