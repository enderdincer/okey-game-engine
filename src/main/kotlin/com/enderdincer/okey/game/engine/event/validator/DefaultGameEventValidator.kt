package com.enderdincer.okey.game.engine.event.validator

import com.enderdincer.okey.game.engine.domain.GameEvent
import com.enderdincer.okey.game.engine.domain.GameState
import com.enderdincer.okey.game.engine.exception.GameEventValidationException
import com.enderdincer.okey.game.engine.exception.InvalidGameConfigException

class DefaultGameEventValidator : GameEventValidator {

    override fun validateCreateGameEvent(gameState: GameState, gameEvent: GameEvent) {
        if (gameState.gameId == null) {
            throw RuntimeException("Game ID is mandatory.")
        }

        if (gameEvent.gameConfig == null) {
            throw InvalidGameConfigException("GameConfig can't be null!")
        }

        // TODO: Other remaining game config validation rules
    }

    override fun validateAddPlayerGameEvent(gameState: GameState, gameEvent: GameEvent) {
        if (gameEvent.players == null) {
            throw GameEventValidationException("GameEvent.players is null.")
        }

        if (gameEvent.players.isEmpty()) {
            throw GameEventValidationException("GameEvent.players is empty.")
        }

        if (gameEvent.players.size > gameState.gameConfig!!.numberOfPlayers) {
            throw GameEventValidationException("Number of players to be added can't exceed the number of players allowed in the game config.")
        }

        if (gameState.players == null) {
            return
        }

        if (gameEvent.players.size + gameState.players!!.size > gameState.gameConfig!!.numberOfPlayers) {
            throw GameEventValidationException("Number of total players can't exceed the number of players allowed in the game config.")
        }
    }

    override fun validateDeclareWinGameEvent(gameState: GameState, gameEvent: GameEvent) {
        // TODO: implement
    }

    override fun validateDiscardTileGameEvent(gameState: GameState, gameEvent: GameEvent) {
        // TODO: implement
    }

    override fun validateStartGameEvent(gameState: GameState, gameEvent: GameEvent) {
        // TODO: implement
    }

    override fun validateDrawTileFromDiscardTileStackGameEvent(gameState: GameState, gameEvent: GameEvent) {
        // TODO: implement
    }

    override fun validateDrawTileFromCenterTileStackGameEvent(gameState: GameState, gameEvent: GameEvent) {
        // TODO: implement
    }
}