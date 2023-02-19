package com.enderdincer.okey.game.engine.model

data class Tile(
        val number: Int? = null,
        val color: TileColor? = null,
        val tileType: TileType = TileType.NUMBER_TILE,
) {
    companion object {
        @JvmStatic
        fun fromString(tileString: String): Tile {
            if (tileString == "X") {
                return Tile()
            }

            if (tileString.length == 2) {
                return Tile(
                        tileString.substring(0, 1).toInt(),
                        TileColor.fromString(tileString.substring(1, 2))
                )
            }

            if (tileString.length == 3 && tileString.substring(2, 3) == "F") {
                return Tile(
                        tileString.substring(0, 1).toInt(),
                        TileColor.fromString(tileString.substring(1, 2)),
                        TileType.FALSE_JOKER
                )
            }

            throw RuntimeException("Tile string not valid!")
        }
    }

    override fun toString(): String {
        if (number == null || color == null) {
            return "X"
        }

        if (tileType == TileType.FALSE_JOKER) {
            return "$number${color.shortName}F"
        }

        return "$number${color.shortName}"
    }
}

enum class TileType {
    FALSE_JOKER, NUMBER_TILE
}

enum class TileColor(
        val colorId: Int,
        val shortName: String
) {
    RED(0, "R"),
    GREEN(1, "G"),
    BLACK(2, "B"),
    YELLOW(3, "Y");

    companion object {
        fun fromString(tileColorString: String): TileColor =
                try {
                    TileColor.values().find { it.shortName == tileColorString }!!
                } catch (e: Exception) {
                    throw RuntimeException("Tile color string not valid!")
                }
    }
}




