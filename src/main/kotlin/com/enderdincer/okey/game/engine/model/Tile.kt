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

            val number = tileString.filter { it.isDigit() }.toInt()
            val suffix = tileString.last().toString()

            if (suffix == "F") {
                return Tile(
                        number,
                        TileColor.fromString(tileString.first { it.isLetter() }.toString()),
                        TileType.FALSE_JOKER
                )
            }

            return Tile(
                    number,
                    TileColor.fromString(suffix),
                    TileType.NUMBER_TILE
            )
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




