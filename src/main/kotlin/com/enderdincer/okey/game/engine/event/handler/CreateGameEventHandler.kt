package com.enderdincer.okey.game.engine.event.handler

import com.enderdincer.okey.game.engine.domain.*
import com.enderdincer.okey.game.engine.event.validator.GameEventValidator

class CreateGameEventHandler(
        private val gameEventValidator: GameEventValidator
) : AbstractGameEventHandler() {

    override fun handle(prevGameState: GameState, gameEvent: GameEvent): GameState {
        gameEventValidator.validateCreateGameEvent(prevGameState, gameEvent)

        val gameConfig = gameEvent.gameConfig!!
        val centerTileStack = mutableListOf<Tile>()

        TileColor.values().forEach { tileColor ->
            (1..gameConfig.numberOfTilesPerTileColor).forEach { tileNumber ->
                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
                centerTileStack.add(Tile(tileNumber, tileColor, TileType.NUMBER_TILE))
            }
        }

        (1..gameConfig.numberOfFalseJokers).forEach { _ ->
            centerTileStack.add(Tile(tileType = TileType.FALSE_JOKER))
        }

        centerTileStack.shuffle()

        return prevGameState.copy(centerTileStack = centerTileStack, gameConfig = gameConfig)
    }
}