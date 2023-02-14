package com.enderdincer.okey.game.engine.model

data class RackEvalResult(
        var isWinning: Boolean = false,
        var allPossibleArrangements: List<List<List<Tile>>> = listOf(),
        var bestArrangementByRemainingTiles: List<List<Tile>> = listOf()
)
