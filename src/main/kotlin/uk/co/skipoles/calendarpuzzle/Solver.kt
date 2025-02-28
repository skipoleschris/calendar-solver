package uk.co.skipoles.calendarpuzzle

fun fitPieces(grid: Grid, pieces: Set<Piece>): Grid? {
    if (pieces.isEmpty()) return grid

    var piece = pieces.first()
    val remainingPieces = pieces - piece
    for (row in 0 until grid.height) {
        for (col in 0 until grid.width) {
            for (rotation in 0..3) {
                piece = piece.rotated()
                val position = Position(row, col)
                if (grid.canPlaceAt(piece, position)) {
                    val newGrid = grid.placeAt(piece, position)
                    if (newGrid.containsIsolatedCells()) {
                        continue
                    }
                    val result = fitPieces(newGrid, remainingPieces)
                    if (result != null) {
                        return result
                    }
                }
            }
        }
    }
    return null
}
