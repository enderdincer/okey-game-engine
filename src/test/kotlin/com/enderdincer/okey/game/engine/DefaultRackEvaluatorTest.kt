package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator
import com.enderdincer.okey.game.engine.model.GameConfigs
import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor
import com.enderdincer.okey.game.engine.model.TileType
import com.enderdincer.okey.game.engine.utils.TestUtils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

class DefaultRackEvaluatorTest {

    private val rackEvaluator = DefaultRackEvaluator(GameConfigs.getDefaultGameConfig())
    private val objectMapper = ObjectMapper()

    @Test
    fun test() {
        val rack = objectMapper.readValue(TestUtils.TILES_NO_DUPLICATES_NO_JOKER_NO_FALSE_JOKER_FILE, object : TypeReference<List<Tile>>() {})
        val joker = Tile(tileType = TileType.NUMBER_TILE, number = 3, color = TileColor.RED)
        val result = rackEvaluator.evaluate(rack, joker)

        println()
    }
}