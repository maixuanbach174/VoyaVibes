package com.example.voyavibes.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph (navController: NavHostController, innerPadding: PaddingValues) {
    val seat = remember { mutableStateOf("")}
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
                onFilterClick = { navController.navigate("FilterScreen") }, onFlightClick = {
                    navController.navigate("SelectSeatsScreen")
                })
        }
        composable("FilterScreen") {
             FilterScreen(onBackClick = { navController.popBackStack() }, onDoneClick = { navController.popBackStack() },
                    onResetClick = { navController.navigate("SelectSeatsScreen")})
        }
        composable("SelectSeatsScreen") {
             SelectSeatScreen(onBackClick = { navController.popBackStack() }, onContinueClick = {
                 seat.value = it
                 navController.navigate("BoardingPassScreen")
             })
        }
        composable("PersonalScreen") {
            PersonalScreen(onBackClick = { navController.popBackStack() })
        }
        composable("BoardingPassScreen") {
            BoardingPassScreen(onBackClick = { navController.popBackStack() }, seat.value)
        }
    }
}

