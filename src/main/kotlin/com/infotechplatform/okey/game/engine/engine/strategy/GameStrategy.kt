package com.infotechplatform.okey.game.engine.engine.strategy

import com.infotechplatform.okey.game.engine.model.Player
import com.infotechplatform.okey.game.engine.model.Tile

interface GameStrategy{

    fun drawTile(freeTiles: MutableList<Tile>)

    fun throwTile()
}