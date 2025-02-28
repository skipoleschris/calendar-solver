package uk.co.skipoles.calendarpuzzle

import java.util.*
import kotlin.system.exitProcess

fun main(
    args : Array<String>
) {
    if (args.size != 3) showUsageAndExit()
    val (month, date, day) = try {
        Triple(
            Months.valueOf(args[0].uppercase(Locale.getDefault())),
            Dates.entries[args[1].toInt() - 1],
            Days.valueOf(args[2].uppercase(Locale.getDefault()))
        )
    } catch (e: Exception) {
        println("Unable to process commandline parameters")
        showUsageAndExit()
    }

    val startingGrid = Grid.emptyGrid(month, date, day)
    val pieces = Pieces.hardPuzzlePieces

    println("Attempting to solve the puzzle...")
    val result = fitPieces(startingGrid, pieces)
    if (result != null) {
        result.cells.forEach { row ->
            println(row.map { it.content }.joinToString(""))
        }
    } else {
        println("No solution found")
    }
}

private fun showUsageAndExit(): Triple<Months, Dates, Days> {
    println("Usage: calendar-puzzle <month> <date> <day>")
    println("Where:")
    println("    <month> is a three-letter month abbreviation (e.g. JAN)")
    println("    <date> is a numeric date in the month (e.g. 28)")
    println("    <day> is a three-letter day abbreviation (e.g. FRI)")
    exitProcess(0)
}
