package com.infotechplatform.okey.game.experimental.strategy//package com.infotechplatform.okey.game.engine.engine.strategy
//
//import com.infotechplatform.okey.game.experimental.TileHandler
//import com.infotechplatform.okey.game.engine.model.Player
//import com.infotechplatform.okey.game.engine.model.Tile
//
////class DummyGameStrategy(
////        private val tileHandler: TileHandler,
////        override val player: Player,
////        override val joker: Tile,
////) : AbstractGameStrategy(player, joker) {
////
////    override fun drawTile(freeTiles: MutableList<Tile>) {
////        tileHandler.draw(
////                this.player,
////                this.player.leftPlayer!!.discardStack
////        )
////    }
////
////    override fun declareWin(): Boolean {
////        return false
////    }
////
////
////    override fun discardTile() {
////        tileHandler.throwPickedTileTo(
////                this.player,
////                this.player.rack[0],
////                this.player.discardStack
////        )
////    }
//}