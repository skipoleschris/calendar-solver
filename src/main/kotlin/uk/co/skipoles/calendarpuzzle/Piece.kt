package uk.co.skipoles.calendarpuzzle


data class Position(
    val row: Int,
    val col: Int
)

data class Piece(
    val id: Char,
    val positions: Set<Position>,
    val width: Int = positions.maxOf { it.col } + 1,
    val height: Int = positions.maxOf { it.row } + 1
) {
    fun rotated(): Piece {
        val newPositions = positions.map { Position(it.col, height - 1 - it.row) }
        return Piece(id, newPositions.toSet())
    }
}

object Pieces {

    val hardPuzzlePieces: Set<Piece> = setOf(
        listOf(
            "AAA",
            "A  ",
            "A  "
        ).toPiece(),
        listOf(
            "BB ",
            " B ",
            " BB"
        ).toPiece(),
        listOf(
            "CCC",
            " C ",
            " C "
        ).toPiece(),
        listOf(
            "DDDD",
            "   D"
        ).toPiece(),
        listOf(
            "EEE",
            "EE "
        ).toPiece(),
        listOf(
            "  FF",
            "FFF "
        ).toPiece(),
        listOf(
            "G G",
            "GGG"
        ).toPiece(),
        listOf(
            "HHHH"
        ).toPiece(),
        listOf(
            " II",
            "II "
        ).toPiece(),
        listOf(
            "JJJ",
            "J  "
        ).toPiece()
    )

    private fun List<String>.toPiece(): Piece {
        var id = ' '
        val positions = this.flatMapIndexed { row, s ->
            s.mapIndexedNotNull { col, ch ->
                if (ch != ' ') {
                    id = ch
                    Position(row, col)
                }
                else null
            }
        }.toSet()
        return Piece(id, positions)
    }
}