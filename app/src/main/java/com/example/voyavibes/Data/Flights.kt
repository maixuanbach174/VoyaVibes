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
    val list_04_July = listOf(
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "7:00 AM", 100.0, "VN 134"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "8:00 AM", 200.0, "VN 374"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "12:00 AM", 100.0, "VN 340"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "3:00 PM", 250.0, "VN 156"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "5:00 PM", 230.0, "VN 221"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "7:00 PM", 140.0, "VN 734"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "9:00 PM", 90.0, "VN 190"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "10:00 PM", 60.0, "VN 132"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "04 July", "11:00 PM", 70.0, "VN 142"),
    )

    val list_05_July = listOf(
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "4:30 AM", 100.0, "VN 134"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "10:20 AM", 200.0, "VN 374"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "12:00 AM", 300.0, "VN 340"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "3:00 PM", 250.0, "VN 156"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "6:00 PM", 230.0, "VN 221"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "7:00 PM", 140.0, "VN 734"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "8:00 PM", 90.0, "VN 190"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "05 July", "10:00 PM", 60.0, "VN 132"),
    )

    val list_06_July = listOf(
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "4:30 AM", 100.0, "VN 134"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "5:30 AM", 200.0, "VN 374"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "12:00 AM", 300.0, "VN 340"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "3:10 PM", 250.0, "VN 156"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "5:30 PM", 230.0, "VN 221"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "7:45 PM", 140.0, "VN 734"),
        Flight("Da Nang", "DAD", "HCM City", "SGN", "06 July", "9:45 PM", 90.0, "VN 190"),
    )
}
