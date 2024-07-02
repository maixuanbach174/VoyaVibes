package com.example.voyavibes.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.voyavibes.navigation.BottomBarScreen
import com.example.voyavibes.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomBar(navController = navController)},
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        NavGraph(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Booking,
        BottomBarScreen.Notifications,
        BottomBarScreen.Account
    )
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.height(80.dp)
    ) {
        screens.forEach { screen ->
                AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
            )
        },
        label = { if(isSelected)Text(text = screen.title, fontWeight = FontWeight.Bold) else Text("") },
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {

            }
        },
        modifier = Modifier.background(if(isSelected) Color(0xE5FFDDA2) else Color.White)
    )
}
