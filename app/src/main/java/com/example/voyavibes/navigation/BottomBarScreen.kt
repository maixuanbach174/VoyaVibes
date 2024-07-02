package com.example.voyavibes.navigation
import com.example.voyavibes.R


sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: Int,
) {
    object Home : BottomBarScreen("Home", "Home", R.drawable.home)
    object Booking : BottomBarScreen("Booking", "Booking", R.drawable.ticket)
    object Notifications : BottomBarScreen("Notify", "Notify", R.drawable.bell)
    object Account : BottomBarScreen("Account", "Account", R.drawable.user)
}
