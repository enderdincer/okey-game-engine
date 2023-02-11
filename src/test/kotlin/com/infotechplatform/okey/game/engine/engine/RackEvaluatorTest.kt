package com.infotechplatform.okey.game.engine.engine
//
//import com.infotechplatform.okey.game.engine.model.NumberTile
//import com.infotechplatform.okey.game.engine.model.TileColor
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.InjectMocks
//import org.mockito.junit.jupiter.MockitoExtension
//
//@ExtendWith(MockitoExtension::class)
//class RackEvaluatorTest {
//
//    @InjectMocks
//    private lateinit var rackEvaluator: RackEvaluator
//
//    @Test
//    fun test() {
//        val givenRack = Rack(
//                numberTiles = mutableListOf(
//                        NumberTile(1, TileColor.RED),
//                        NumberTile(2, TileColor.RED),
//                        NumberTile(3, TileColor.RED),
//
//                        NumberTile(7, TileColor.RED),
//                        NumberTile(8, TileColor.RED),
//                        NumberTile(9, TileColor.RED),
//
//                        NumberTile(5, TileColor.RED),
//                        NumberTile(5, TileColor.BLACK),
//                        NumberTile(5, TileColor.YELLOW),
//                        NumberTile(5, TileColor.GREEN),
//
//                        NumberTile(4, TileColor.YELLOW),
//                        NumberTile(5, TileColor.YELLOW),
//                        NumberTile(6, TileColor.YELLOW),
//                        NumberTile(7, TileColor.YELLOW),
//
//                        NumberTile(13, TileColor.GREEN),
//                ),
//                jokerTiles = mutableListOf()
//        )
//
//        rackEvaluator.zaxd(givenRack, NumberTile(6, TileColor.BLACK))
//    }
//}