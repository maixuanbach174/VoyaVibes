package com.example.voyavibes.BackEnd

data class Seat(
    val row: Int,
    val column: Char,
    val seat: String,
    var status: Int, // Change status to var to allow updates
    val price: Double,
)

object SeatTable {
    private val seats: MutableList<MutableList<Seat>> = generateSeats(37, listOf('A', 'B', 'C', 'D'))
    private fun generateSeats(rows: Int = 37, columns: List<Char> = listOf('A', 'B', 'C', 'D') , price: Double = 100.0): MutableList<MutableList<Seat>> {
        val seatList = mutableListOf<MutableList<Seat>>()

        for (row in 1..rows) {
            val rowList = mutableListOf<Seat>()
            for (column in columns) {
                val seat = Seat(row, column, "$row$column", 2, price)
                rowList.add(seat)
            }
            seatList.add(rowList)
        }

        return seatList
    }

    fun getSeats(): MutableList<MutableList<Seat>> {
        return seats
    }

    fun updateSeatStatus(row: Int, column: Char, newStatus: Int) {
        val seat = seats.getOrNull(row - 1)?.find { it.column == column }
        seat?.status = newStatus
    }
}
