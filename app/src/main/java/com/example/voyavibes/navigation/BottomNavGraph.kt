package com.example.voyavibes.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.voyavibes.screens.BoardingPassScreen
import com.example.voyavibes.screens.FilterScreen
import com.example.voyavibes.screens.HomeScreen
import com.example.voyavibes.screens.NotificationsScreen
import com.example.voyavibes.screens.PersonalScreen
import com.example.voyavibes.screens.ProfileScreen
import com.example.voyavibes.screens.SelectSeatScreen
import com.example.voyavibes.screens.TicketsScreen
import com.example.voyavibes.screens.TransportBookingScreen
import com.example.voyavibes.screens.TransportFlights


@Composable
fun NavGraph (navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController, startDestination = BottomBarScreen.Home.route,
        enterTransition = {EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ){
        composable(BottomBarScreen.Home.route) {
            HomeScreen(innerPadding
            ) { navController.navigate("TransportBooking") }
        }
        composable(BottomBarScreen.Booking.route) {
            TicketsScreen(innerPadding,
                {},
                {},
                {
                    navController.navigate("TransportBooking")
                },
                {}
            )
        }
        composable(BottomBarScreen.Notifications.route) {
            NotificationsScreen(innerPadding)
        }
        composable(BottomBarScreen.Account.route) {
            ProfileScreen(innerPadding, onDetailClick = { navController.navigate("PersonalScreen") })
        }
        composable("TransportBooking") {
            TransportBookingScreen(
                onBackClick = { navController.popBackStack() },
                onSearchClick = { navController.navigate("TransportFlights") }
            )
        }
        composable("TransportFlights") {
            TransportFlights(onBackClick = { navController.popBackStack() },
                onFilterClick = { navController.navigate("FilterScreen") })
        }
        composable("FilterScreen") {
             FilterScreen(onBackClick = { navController.popBackStack() }, onDoneClick = { navController.popBackStack() },
                    onResetClick = { navController.navigate("SelectSeatsScreen")})
        }
        composable("SelectSeatsScreen") {
             SelectSeatScreen(onBackClick = { navController.popBackStack() }, onContinueClick = { navController.navigate("BoardingPassScreen") })
        }
        composable("PersonalScreen") {
            PersonalScreen(onBackClick = { navController.popBackStack() })
        }
        composable("BoardingPassScreen") {
            BoardingPassScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

