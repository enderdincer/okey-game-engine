package com.infotechplatform.okey.game.engine.engine.strategy

import com.infotechplatform.okey.game.engine.engine.RackHelper
import com.infotechplatform.okey.game.engine.engine.TileHandler
import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

class SimpleCompleteGameStrategy(
        private val tileHandler: TileHandler,
        private val rackHelper: RackHelper,
        override val player: Player,
        override val currentOkey: NumberTile
) : AbstractGameStrategy(player, currentOkey) {

    override fun drawTile(freeTiles: MutableList<Tile>) {

    }

    override fun declareWin(): Boolean {
        return false
    }

    override fun discardTile() {

    }
}