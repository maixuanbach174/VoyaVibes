package com.example.voyavibes.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.voyavibes.screens.HomeScreen
import com.example.voyavibes.screens.NotificationsScreen
import com.example.voyavibes.screens.ProfileScreen
import com.example.voyavibes.screens.TicketsScreen

@Composable
fun MiddleNavGraph (navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(innerPadding)
        }
        composable(BottomBarScreen.Tickets.route) {
            TicketsScreen(innerPadding)
        }
        composable(BottomBarScreen.Notifications.route) {
            NotificationsScreen(innerPadding)
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(innerPadding)
        }
    }
}