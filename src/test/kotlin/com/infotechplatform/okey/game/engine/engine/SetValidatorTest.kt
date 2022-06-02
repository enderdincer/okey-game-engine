package com.infotechplatform.okey.game.engine.engine

import com.infotechplatform.okey.game.engine.model.JokerTile
import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.TileColor
import com.infotechplatform.okey.game.engine.model.TileSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SetValidatorTest {

    @InjectMocks
    private lateinit var setValidator: SetValidator

    @Test
    fun `A sequence of 3 numbers should be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(1, TileColor.GREEN),
                NumberTile(2, TileColor.GREEN),
                NumberTile(3, TileColor.GREEN),
        ), listOf(JokerTile()))
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = true

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Not complete sequence should not be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(3, TileColor.GREEN),
                NumberTile(2, TileColor.GREEN),
                NumberTile(2, TileColor.RED),
        ), listOf(JokerTile()))
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Repeating numbers with same color should not be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.BLACK),
                NumberTile(2, TileColor.BLACK),
                NumberTile(2, TileColor.RED),
        ), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 3 same numbers of unique colors should be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(7, TileColor.GREEN),
                NumberTile(7, TileColor.BLACK),
                NumberTile(7, TileColor.RED),
        ), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = true

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 4 same numbers of unique colors should be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(3, TileColor.GREEN),
                NumberTile(3, TileColor.BLACK),
                NumberTile(3, TileColor.RED),
                NumberTile(3, TileColor.YELLOW),
        ), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = true

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 2 same numbers of unique colors should not be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.GREEN),
                NumberTile(2, TileColor.BLACK),
        ), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 1 same numbers of unique colors should not be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.GREEN),
        ), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 0 same numbers of unique colors should not be a valid set`(){
        val givenTileSet = TileSet(listOf(), listOf())
        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 2 same numbers of unique colors with one joker tile matches the set should be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.GREEN),
                NumberTile(2, TileColor.BLACK),
        ), listOf(JokerTile()))
        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
        val expectedResult = true

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 3 same numbers of unique colors with one joker tile matches the set should be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.GREEN),
                NumberTile(2, TileColor.BLACK),
                NumberTile(2, TileColor.RED),
        ), listOf(JokerTile()))
        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
        val expectedResult = true

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }

    @Test
    fun `Group of 3 same numbers of unique colors with one joker tile that is a duplicate should not be a valid set`(){
        val givenTileSet = TileSet(listOf(
                NumberTile(2, TileColor.GREEN),
                NumberTile(2, TileColor.BLACK),
                NumberTile(2, TileColor.YELLOW),
        ), listOf(JokerTile()))
        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
        val expectedResult = false

        val isValid = setValidator.isValid(givenTileSet, givenCurrentOkey)

        assertThat(isValid).isEqualTo(expectedResult)
    }
}