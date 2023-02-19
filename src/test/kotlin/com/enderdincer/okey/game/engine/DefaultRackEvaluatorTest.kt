package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.evaluator.DefaultRackEvaluator
import com.enderdincer.okey.game.engine.evaluator.Evaluators
import com.enderdincer.okey.game.engine.model.GameConfigs
import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor
import com.enderdincer.okey.game.engine.model.TileType
import com.enderdincer.okey.game.engine.utils.TestUtils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class DefaultRackEvaluatorTest {

    private val rackEvaluator = Evaluators.getDefaultRackEvaluator()
    private val objectMapper = ObjectMapper()

    @Test
    fun test() {
        val rack = objectMapper.readValue(TestUtils.TILES_NO_DUPLICATES_NO_JOKER_NO_FALSE_JOKER_FILE, object : TypeReference<List<Tile>>() {})
        val joker = Tile(tileType = TileType.NUMBER_TILE, number = 7, color = TileColor.RED)
        val result = rackEvaluator.evaluate(rack, joker)

        println()
    }

    @Test
    fun test2() {
        val rack = objectMapper.readValue(TestUtils.TILES_NO_JOKER_NO_FALSE_JOKER_FILE, object : TypeReference<List<Tile>>() {})
        val joker = Tile(tileType = TileType.NUMBER_TILE, number = 7, color = TileColor.RED)
        val result = rackEvaluator.evaluate(rack, joker)

        println()
    }

    @Test
    fun test3(){
        val rack = objectMapper.readValue(TestUtils.WINNING_RACK_FILE, object : TypeReference<List<Tile>>() {})
        Assertions.assertThat(rack).hasSize(15)

        val joker = Tile(tileType = TileType.NUMBER_TILE, number = 8, color = TileColor.RED)
        val result = rackEvaluator.evaluate(rack, joker)

        println()
    }

    @Test
    fun test4(){
        val rack = objectMapper.readValue(File("src/test/resources/jsons/test.json"), object : TypeReference<List<Tile>>() {})
        Assertions.assertThat(rack).hasSize(8)

        val joker = Tile(tileType = TileType.NUMBER_TILE, number = 8, color = TileColor.RED)
        val result = rackEvaluator.evaluate(rack, joker)

        println()
    }
}