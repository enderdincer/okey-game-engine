package com.infotechplatform.okey.game.engine.engine.strategy

import com.infotechplatform.okey.game.engine.model.NumberTile
import com.infotechplatform.okey.game.engine.model.Player

abstract class AbstractGameStrategy(
        protected open val player: Player,
        protected open val currentOkey: NumberTile
) : GameStrategy