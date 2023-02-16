package com.enderdincer.okey.game.engine.model

data class RackEvalResult(
        var isWinning: Boolean = false,
        var allPossibleRackArrangements: List<RackArrangement> = listOf(),
        var bestArrangementByRemainingTiles: RackArrangement
)
