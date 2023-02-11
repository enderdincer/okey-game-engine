package com.infotechplatform.okey.game.experimental.strategy//package com.infotechplatform.okey.game.engine.engine.strategy
//
//import com.infotechplatform.okey.game.experimental.RackHelper
//import com.infotechplatform.okey.game.experimental.TileHandler
//import com.infotechplatform.okey.game.engine.model.*
//
//class SimpleCompleteGameStrategy(
//        private val tileHandler: TileHandler,
//        private val rackHelper: RackHelper,
//        override val joker: Tile
//) : AbstractGameStrategy(joker) {
//
//    override fun drawTile(freeTiles: MutableList<Tile>) {
//
//    }
//
//    override fun declareWin(): Boolean {
//        return false
//    }
//
//    override fun discardTile() {
//
//    }
//
//
//    fun findAllRackArrangements(rack: List<Tile>): List<List<List<Tile>>> {
//        return listOf()
//    }
//
//    private fun findAllSets(rack: List<Tile>): List<List<Tile>> {
//        val allGroupsAndAllRuns = findAllGroups(rack).toMutableList()
//        allGroupsAndAllRuns.addAll(findAllRuns(rack))
//        return allGroupsAndAllRuns
//    }
//
//    private fun findAllGroups(rack: List<Tile>): List<List<Tile>> {
//        return listOf()
//    }
//
//    private fun findAllGroupsByMinLength(minLength: Int, rack: List<Tile>): List<List<Tile>> {
//        val result = mutableListOf<Set<Tile>>()
//        for (number in 1..13) {
//            val group = rack
//                    .filter { it.number == number }
//                    .filter { it != joker }
//                    .toSet()
//            if(group.size >= minLength){
//                result.add(group)
//                if (group.size == 4) {
//                    for(tile in group){
//                        result.add(group.filter { it != tile }.toSet())
//                    }
//                }
//            }
//        }
//        return result.map { it.toList() }
//    }
//
//    private fun findAllRuns(rack: List<Tile>): List<List<Tile>> {
//        return listOf()
//    }
//}