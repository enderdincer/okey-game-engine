package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.commons.TileHelper
import com.enderdincer.okey.game.engine.evaluator.Evaluators
import com.enderdincer.okey.game.engine.evaluator.pair.DefaultTilePairEvaluator
import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor
import com.enderdincer.okey.game.engine.model.TileType
import com.enderdincer.okey.game.engine.utils.TestUtils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DefaultRackEvaluatorTest {

    private val rackEvaluator = Evaluators.getDefaultRackEvaluator()

    @Test
    fun `GIVEN a not winning rack with tiles in the form of 12-13-1-2 THEN rack should not be winning`(){
        val rack = TileHelper
                .getTilesFromString("11B, 12b, 13B, 1B, 2B, 10G, 11G, 12G, 13G, 1G, 5Y, 5G, 5B, 5R, 11B")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isFalse
    }

    @Test
    fun `GIVEN rack with 12-13-1 run type THEN rack should be winning`(){
        val rack = TileHelper
                .getTilesFromString("11B, 12b, 13B, 1B, 9G, 10G, 11G, 12G, 13G, 1G, 5Y, 5G, 5B, 5R, 11B")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }

    @Test
    fun `GIVEN rack with sets of 4, 4, 3 and 3 THEN rack should be winning`(){
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 3G, 4G, 9G, 10G, 11G, 12G, 4Y, 5Y, 6Y, 12R, 12G, 12B, 5B")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }

    @Test
    fun `GIVEN rack with shuffled 7 pairs and no jokers THEN rack should be winning`(){
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 9G, 7B, 9G, 4Y, 12R, 4B, 4Y, 7B, 4B, 12R, 1G, 2G, 5Y")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }

    @Test
    fun `GIVEN rack with shuffled 6 pairs and one joker used THEN rack should be winning`(){
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 9G, 7B, 9G, 4Y, 12R, 4B, 4Y, 7B, 4B, 6R, 1G, 2G, 5Y")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }

    @Test
    fun `GIVEN rack with shuffled 5 pairs and two jokers used THEN rack should be winning`(){
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 9G, 7B, 9G, 4Y, 12R, 6R, 4Y, 7B, 4B, 6R, 1G, 2G, 5Y")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }

    @Test
    fun `GIVEN rack with shuffled 6 pairs and two jokers used THEN rack should be winning by discarding a joker`(){
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 9G, 7B, 9G, 4Y, 12R, 6R, 4Y, 7B, 4B, 6R, 1G, 2G, 4B")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }
}