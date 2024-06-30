package com.example.voyavibes.navigation
import com.example.voyavibes.R


sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: Int,
    val selectedIcon: Int
) {
    object Home : BottomBarScreen("home", "Home", R.drawable.home, R.drawable.activehome)
    object Tickets : BottomBarScreen("tickets", "Tickets", R.drawable.ticket, R.drawable.activebooking)
    object Notifications : BottomBarScreen("notifications", "Bell", R.drawable.bell, R.drawable.activenotification)
    object Profile : BottomBarScreen("Account", "Account", R.drawable.user, R.drawable.activeaccount)
}
