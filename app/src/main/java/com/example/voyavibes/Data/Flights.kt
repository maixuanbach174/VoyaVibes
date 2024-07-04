package com.example.voyavibes.Data

data class Flight(
    val departureName: String,
    val departureCode: String,
    val arrivalName: String,
    val arrivalCode: String,
    val date: String,
    val departureTime: String,
    val price: Double,
    val flightNumber: String
)

object Flights {
    val list = listOf(
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "9:00 AM", 200.0, "AA 13"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "0:00 PM", 200.0, "AA 14"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "1:00 PM", 200.0, "AA 15"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "3:00 PM", 200.0, "AA 16"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "5:00 PM", 200.0, "AA 17"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "7:00 PM", 200.0, "AA 18"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "9:00 PM", 200.0, "AA 19"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "10:00 PM", 200.0, "AA 10"),
        Flight("New York", "NYC", "London", "LDN", "02 Jun", "11:00 PM", 200.0, "AA 11"),
    )
}
