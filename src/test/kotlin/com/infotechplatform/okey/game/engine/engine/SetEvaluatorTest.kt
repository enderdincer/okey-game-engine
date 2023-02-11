package com.infotechplatform.okey.game.engine.engine
//
//import com.infotechplatform.okey.game.engine.model.JokerTile
//import com.infotechplatform.okey.game.engine.model.NumberTile
//import com.infotechplatform.okey.game.engine.model.TileColor
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.InjectMocks
//import org.mockito.junit.jupiter.MockitoExtension
//
//@ExtendWith(MockitoExtension::class)
//class SetEvaluatorTest {
//
//    @InjectMocks
//    private lateinit var setEvaluator: SetEvaluator
//
//    @Test
//    fun `A sequence of 3 numbers should be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(1, TileColor.GREEN),
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(3, TileColor.GREEN),
//        ), mutableListOf(JokerTile()))
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = true
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Not complete sequence should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(3, TileColor.GREEN),
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(2, TileColor.RED),
//        ), mutableListOf(JokerTile()))
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Repeating numbers with same color should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.BLACK),
//                NumberTile(2, TileColor.BLACK),
//                NumberTile(2, TileColor.RED),
//        ), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 3 same numbers of unique colors should be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(7, TileColor.GREEN),
//                NumberTile(7, TileColor.BLACK),
//                NumberTile(7, TileColor.RED),
//        ), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = true
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 4 same numbers of unique colors should be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(3, TileColor.GREEN),
//                NumberTile(3, TileColor.BLACK),
//                NumberTile(3, TileColor.RED),
//                NumberTile(3, TileColor.YELLOW),
//        ), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = true
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 2 same numbers of unique colors should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(2, TileColor.BLACK),
//        ), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 1 same numbers of unique colors should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.GREEN),
//        ), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 0 same numbers of unique colors should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(), mutableListOf())
//        val givenCurrentOkey = NumberTile(5, TileColor.GREEN)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 2 same numbers of unique colors with one joker tile matches the set should be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(2, TileColor.BLACK),
//        ), mutableListOf(JokerTile()))
//        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
//        val expectedResult = true
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 3 same numbers of unique colors with one joker tile matches the set should be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(2, TileColor.BLACK),
//                NumberTile(2, TileColor.RED),
//        ), mutableListOf(JokerTile()))
//        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
//        val expectedResult = true
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//
//    @Test
//    fun `Group of 3 same numbers of unique colors with one joker tile that is a duplicate should not be a valid set`(){
//        val givenTileSet = TileSet(mutableListOf(
//                NumberTile(2, TileColor.GREEN),
//                NumberTile(2, TileColor.BLACK),
//                NumberTile(2, TileColor.YELLOW),
//        ), mutableListOf(JokerTile()))
//        val givenCurrentOkey = NumberTile(2, TileColor.YELLOW)
//        val expectedResult = false
//
//        val isValid = setEvaluator.isValid(givenTileSet, givenCurrentOkey)
//
//        assertThat(isValid).isEqualTo(expectedResult)
//    }
//}