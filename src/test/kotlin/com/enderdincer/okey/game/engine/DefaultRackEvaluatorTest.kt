package com.enderdincer.okey.game.engine

import com.enderdincer.okey.game.engine.commons.TileHelper
import com.enderdincer.okey.game.engine.evaluator.Evaluators
import com.enderdincer.okey.game.engine.model.Tile
import com.enderdincer.okey.game.engine.model.TileColor
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DefaultRackEvaluatorTest {

    private val rackEvaluator = Evaluators.getDefaultRackEvaluator()

    @ParameterizedTest
    @CsvSource(value = [
        "12-13-1-2 SHOULD NOT WIN _ 11B, 12b, 13B, 1B, 2B, 10G, 11G, 12G, 13G, 1G, 5Y, 5G, 5B, 5R, 11B _ 6R _ false",
        "RACK WITH 12-13-1 SHOULD WIN _ 11B, 12b, 13B, 1B, 9G, 10G, 11G, 12G, 13G, 1G, 5Y, 5G, 5B, 5R, 11B _ 6R _ true",
        "SETS OF 4,4,3,3 SHOULD WIN _ 1G, 2G, 3G, 4G, 9G, 10G, 11G, 12G, 4Y, 5Y, 6Y, 12R, 12G, 12B, 5B _ 6R _ true",
        "7 PAIRS SHOULD WIN _ 1G, 2G, 3G, 4G, 9G, 10G, 11G, 12G, 4Y, 5Y, 6Y, 12R, 12G, 12B, 5B _ 6R _ true",
        "6 PAIRS AND A JOKER SHOULD WIN _ 1G, 2G, 9G, 7B, 9G, 4Y, 12R, 4B, 4Y, 7B, 4B, 6R, 1G, 2G, 5Y _ 6R _ true",
        "5 PAIRS AND 2 JOKERS SHOULD WIN _ 1G, 2G, 9G, 7B, 9G, 4Y, 12R, 4B, 4Y, 7B, 4B, 6R, 1G, 2G, 5Y _ 6R _ true",
    ], delimiter = '_')
    fun `Parameterized RackEvaluator Test`(testName: String, rackString: String, jokerString: String, isWinning: Boolean) {
        println("Test: $testName")
        val shuffledRack = TileHelper.getTilesFromString(rackString).shuffled()
        val jokerTile = Tile.fromString(jokerString)
        val expectedGameResult = isWinning

        Assertions.assertThat(shuffledRack).hasSize(15)

        val evaluationResult = rackEvaluator.evaluate(shuffledRack, jokerTile)
        Assertions.assertThat(evaluationResult.isWinning).isEqualTo(expectedGameResult)
    }

    @Test
    fun `GIVEN rack with shuffled 6 pairs and two jokers used THEN rack should be winning by discarding a joker`() {
        val rack = TileHelper
                .getTilesFromString("1G, 2G, 9G, 7B, 9G, 4Y, 12R, 6R, 4Y, 7B, 4B, 6R, 1G, 2G, 4B")
                .shuffled()
        val joker = Tile(number = 6, color = TileColor.RED)
        Assertions.assertThat(rack).hasSize(15)

        val evalResult = rackEvaluator.evaluate(rack, joker)
        Assertions.assertThat(evalResult.isWinning).isTrue
    }
}