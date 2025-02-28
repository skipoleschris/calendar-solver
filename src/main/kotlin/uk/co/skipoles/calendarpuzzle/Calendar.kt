package uk.co.skipoles.calendarpuzzle

interface CalendarItem

enum class Months: CalendarItem {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
}

enum class Dates: CalendarItem {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
    ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY,
    TWENTY_ONE, TWENTY_TWO, TWENTY_THREE, TWENTY_FOUR, TWENTY_FIVE, TWENTY_SIX, TWENTY_SEVEN,
    TWENTY_EIGHT, TWENTY_NINE, THIRTY, THIRTY_ONE
}

enum class Days: CalendarItem {
    SUN, MON, TUE, WED, THU, FRI, SAT
}

enum class NonUsable: CalendarItem {
    NON_USABLE
}
