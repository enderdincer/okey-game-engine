package com.enderdincer.okey.game.engine.model

data class RackEvalResult(
        var isWinning: Boolean = false,
        val allRackArrangements: MutableList<RackArrangement> = mutableListOf(),
)
