package com.example.voyavibes.BackEnd

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import kotlin.random.Random

data class Seat(
    val row: Int,
    val column: Char,
    val seat: String,
    var status: MutableIntState,
    val price: Double,
)

object SeatTable {
    private val seats: MutableList<MutableList<Seat>> = generateSeats(37, listOf('A', 'B', 'C', 'D'))
    private fun generateSeats(rows: Int = 37, columns: List<Char> = listOf('A', 'B', 'C', 'D') , price: Double = 100.0): MutableList<MutableList<Seat>> {
        val seatList = mutableListOf<MutableList<Seat>>()

        for (row in 1..10) {
            val rowList = mutableListOf<Seat>()
            for (column in columns) {
                val seat = Seat(row, column, "$row$column", mutableIntStateOf(1), price)
                rowList.add(seat)
            }
            seatList.add(rowList)
        }

        for (row in 11..rows) {
            val rowList = mutableListOf<Seat>()
            for (column in columns) {
                val seat = Seat(row, column, "$row$column", mutableIntStateOf(2), price)
                rowList.add(seat)
            }
            seatList.add(rowList)
        }

        return shuffle2DList(seatList)
//
//        return seatList
    }

    fun getSeats(): MutableList<MutableList<Seat>> {
        return seats
    }

//    fun updateSeatStatus(row: Int, column: Char, newStatus: Int) {
//        val seat = seats.getOrNull(row - 1)?.find { it.column == column }
//        seat?.status = newStatus
//    }
}

fun <T> shuffle2DList(matrix: MutableList<MutableList<T>>): MutableList<MutableList<T>> {
    val flatList = matrix.flatten().toMutableList()
    flatList.shuffle(Random(System.currentTimeMillis()))

    val rows = matrix.size
    val cols = if (rows > 0) matrix[0].size else 0

    val shuffledMatrix = MutableList(rows) { MutableList(cols) { flatList.removeAt(0) } }
    return shuffledMatrix
}
