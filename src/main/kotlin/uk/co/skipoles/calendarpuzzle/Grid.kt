package uk.co.skipoles.calendarpuzzle

data class Cell(
    val item: CalendarItem,
    val content: Char = ' '
) {
    fun isOccupied() = content != ' '

    companion object {
        fun cellFrom(item: CalendarItem, disabledIf: CalendarItem) =
            if (item == disabledIf) Cell(item, '*') else Cell(item)
        fun nonUsableCell() = Cell(NonUsable.NON_USABLE, '#')
    }
}

class Grid(
    val cells: List<List<Cell>>,
    val width: Int = cells[0].size,
    val height: Int = cells.size
) {
    fun canPlaceAt(piece: Piece, position: Position): Boolean {
        if (position.col + piece.width > width || position.row + piece.height > height) {
            return false
        }
        return piece.positions.all { cell ->
            !cells[position.row + cell.row][position.col + cell.col].isOccupied()
        }
    }

    fun placeAt(piece: Piece, position: Position): Grid {
        val newCells = cells.map { it.toMutableList() }
        piece.positions.forEach { cell ->
            newCells[position.row + cell.row][position.col + cell.col] =
                cells[position.row + cell.row][position.col + cell.col].copy(content = piece.id)
        }
        return Grid(newCells)
    }

    fun containsIsolatedCells(): Boolean {
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (cells[row][col].content == ' ') {
                    val neighbours = listOf(
                        Position(row - 1, col),
                        Position(row + 1, col),
                        Position(row, col - 1),
                        Position(row, col + 1)
                    ).filter { it.row in 0 until height && it.col in 0 until width }
                    if (neighbours.all { cells[it.row][it.col].content != ' ' }) {
                        return true
                    }
                }
            }
        }
        return false
    }

    companion object {
        fun emptyGrid(
            month: Months,
            date: Dates,
            day: Days
        ) = Grid(
            listOf(
                listOf(
                    Cell.cellFrom(Months.JAN, month),
                    Cell.cellFrom(Months.FEB, month),
                    Cell.cellFrom(Months.MAR, month),
                    Cell.cellFrom(Months.APR, month),
                    Cell.cellFrom(Months.MAY, month),
                    Cell.cellFrom(Months.JUN, month),
                    Cell.nonUsableCell()
                ),
                listOf(
                    Cell.cellFrom(Months.JUL, month),
                    Cell.cellFrom(Months.AUG, month),
                    Cell.cellFrom(Months.SEP, month),
                    Cell.cellFrom(Months.OCT, month),
                    Cell.cellFrom(Months.NOV, month),
                    Cell.cellFrom(Months.DEC, month),
                    Cell.nonUsableCell()
                ),
                listOf(
                    Cell.cellFrom(Dates.ONE, date),
                    Cell.cellFrom(Dates.TWO, date),
                    Cell.cellFrom(Dates.THREE, date),
                    Cell.cellFrom(Dates.FOUR, date),
                    Cell.cellFrom(Dates.FIVE, date),
                    Cell.cellFrom(Dates.SIX, date),
                    Cell.cellFrom(Dates.SEVEN, date),
                ),
                listOf(
                    Cell.cellFrom(Dates.EIGHT, date),
                    Cell.cellFrom(Dates.NINE, date),
                    Cell.cellFrom(Dates.TEN, date),
                    Cell.cellFrom(Dates.ELEVEN, date),
                    Cell.cellFrom(Dates.TWELVE, date),
                    Cell.cellFrom(Dates.THIRTEEN, date),
                    Cell.cellFrom(Dates.FOURTEEN, date),
                ),
                listOf(
                    Cell.cellFrom(Dates.FIFTEEN, date),
                    Cell.cellFrom(Dates.SIXTEEN, date),
                    Cell.cellFrom(Dates.SEVENTEEN, date),
                    Cell.cellFrom(Dates.EIGHTEEN, date),
                    Cell.cellFrom(Dates.NINETEEN, date),
                    Cell.cellFrom(Dates.TWENTY, date),
                    Cell.cellFrom(Dates.TWENTY_ONE, date),
                ),
                listOf(
                    Cell.cellFrom(Dates.TWENTY_TWO, date),
                    Cell.cellFrom(Dates.TWENTY_THREE, date),
                    Cell.cellFrom(Dates.TWENTY_FOUR, date),
                    Cell.cellFrom(Dates.TWENTY_FIVE, date),
                    Cell.cellFrom(Dates.TWENTY_SIX, date),
                    Cell.cellFrom(Dates.TWENTY_SEVEN, date),
                    Cell.cellFrom(Dates.TWENTY_EIGHT, date),
                ),
                listOf(
                    Cell.cellFrom(Dates.TWENTY_NINE, date),
                    Cell.cellFrom(Dates.THIRTY, date),
                    Cell.cellFrom(Dates.THIRTY_ONE, date),
                    Cell.cellFrom(Days.SUN, day),
                    Cell.cellFrom(Days.MON, day),
                    Cell.cellFrom(Days.TUE, day),
                    Cell.cellFrom(Days.WED, day),
                ),
                listOf(
                    Cell.nonUsableCell(),
                    Cell.nonUsableCell(),
                    Cell.nonUsableCell(),
                    Cell.nonUsableCell(),
                    Cell.cellFrom(Days.THU, day),
                    Cell.cellFrom(Days.FRI, day),
                    Cell.cellFrom(Days.SAT, day)
                )
            )
        )
    }
}