Calendar Puzzle Solver
======================

A friend gave us a calendar puzzle. Each day you have to arrange the
wooden pieces into the grid so that the current Day, Date and Month
are visible. The grid looks like this:

```
    JAN | FEB | MAR | APR | MAY | JUN | ###
   -----+-----+-----+-----+-----+-----+----- 
    JUL | AUG | SEP | OCT | NOV | DEC | ###
   -----+-----+-----+-----+-----+-----+----- 
     1  |  2  |  3  |  4  |  5  |  6  |  7
   -----+-----+-----+-----+-----+-----+----- 
     8  |  9  | 10  | 11  | 12  | 13  | 14
   -----+-----+-----+-----+-----+-----+----- 
    15  | 16  | 17  | 18  | 19  | 20  | 21
   -----+-----+-----+-----+-----+-----+----- 
    22  | 23  | 24  | 25  | 26  | 27  | 28 
   -----+-----+-----+-----+-----+-----+-----
    29  | 30  | 31  | SUN | MON | TUE | WED
   -----+-----+-----+-----+-----+-----+-----
    ### | ### | ### | ### | THU | FRI | SAT
```

The puzzle pieces are as follows:

```
    X        XX       XX      X         X
    X       XX        X       XXXX      XXX
    XXX              XX                 X
    
    XXX     XXX        XX     XXXX      XXX 
    XX      X        XXX                X X
```

The pieces can be rotated, but are not allowed to be flipped as
the wooden surface on the back is not as polished in the same way
as the front.

The puzzle is surprisingly difficult due to the size and shape
of the different pieces. You can even buy a simpler tile set because
it is so hard to solve!

Usage
-----
This software implements an algorithm to solve the puzzle for
each day. It can be invoked as follows:

```
    Usage: calendar-puzzle <month> <date> <day>
    Where:
        <month> is a three-letter month abbreviation (e.g. JAN)
        <date> is a numeric date in the month (e.g. 28)
        <day> is a three-letter day abbreviation (e.g. FRI)
```

Implementation
--------------
The implementation hardcodes a set of `Piece` instances, one for each
of the tiles. These are stored as a set of positions based on a (0, 0)
coordinate system. There is a rotate function that rotates the shape
90-degrees clockwise. The pieces are ordered so that the bigger
and more complex shapes are positioned first as this makes finding the
solution quicker.

Secondly there is a grid the is coded to represent the puzzle grid.
It starts out marking the month, date and day as taken positions.

A recursive algorithm is then applied. It takes the next available
piece and positions it at each location on the grid in each of its
four rotation positions. For each case it then calls the recursive
function with the updated grid and the remaining pieces.

If the recursive function finds a solution it returns the grid with
all the pieces positioned. If the is no place to put the current piece
then the function returns a failure state.

To speed the algorithm up, we also check the grid to look for any
cells that have no empty neighbours. If we find any we can immediately
return a failure state as there's no way we can place all the remaining
pieces.
