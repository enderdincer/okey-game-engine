package com.infotechplatform.okey.game.engine.engine.strategy

import com.infotechplatform.okey.game.engine.engine.TileHandler
import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

class DummyGameStrategy(
        private val tileHandler: TileHandler,
        override val player: Player
) : AbstractGameStrategy(player) {

    override fun drawTile(freeTiles: MutableList<Tile>) {
        tileHandler.drawFrom(
                this.player,
                this.player.leftPlayer!!.tileStack
        )
    }

    override fun throwTile() {
        tileHandler.throwPickedTileTo(
                this.player,
                this.player.rack.numberTiles[0],
                this.player.tileStack
        )
    }
}