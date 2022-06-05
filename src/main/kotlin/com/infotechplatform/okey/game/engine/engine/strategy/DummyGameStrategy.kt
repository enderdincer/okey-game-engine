package com.infotechplatform.okey.game.engine.engine.strategy

import com.infotechplatform.okey.game.engine.engine.TileHandler
import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

class DummyGameStrategy(
        private val tileHandler: TileHandler,
        override val player: Player,
        override val currentOkey: NumberTile,
) : AbstractGameStrategy(player, currentOkey) {

    override fun drawTile(freeTiles: MutableList<Tile>) {
        tileHandler.drawFrom(
                this.player,
                this.player.leftPlayer!!.tileStack
        )
    }

    override fun declareWin(): Boolean {
        return false
    }


    override fun discardTile() {
        tileHandler.throwPickedTileTo(
                this.player,
                this.player.rack.numberTiles[0],
                this.player.tileStack
        )
    }
}