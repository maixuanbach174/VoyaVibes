package com.example.voyavibes.Data

data class Airport(
    val airportCode: String,
    val airportName: String
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$airportName ($airportCode)",
            "$airportName $airportCode",
            "$airportCode $airportName",
            "$airportName$airportCode",
            "$airportCode$airportName"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

public val allAirports = listOf(
    Airport(airportCode = "DAD", airportName = "Da Nang"),
    Airport(airportCode = "HAN", airportName = "Hanoi"),
    Airport(airportCode = "SGN", airportName = "HCM City"),
    Airport(airportCode = "NHA", airportName = "Nha Trang"),
    Airport(airportCode = "DLI", airportName = "Dalat"),
    Airport(airportCode = "VCA", airportName = "Can Tho"),
    Airport(airportCode = "HPH", airportName = "Haiphong"),
)