package com.example.tweetapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tweetapp.model.BottomNavItem
import com.example.tweetapp.navigation.Routes

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navController1  = rememberNavController()

    Scaffold(bottomBar = { MyBottomBar(navController1) }) { innerPadding ->
        NavHost(
            navController = navController1,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Routes.Home.route) {
                Home(navHostController = navController)
            }

            composable(Routes.Search.route) {
                Search(navHostController = navController)
            }

            composable(Routes.AddThread.route) {
                AddThreads(navHostController = navController1)
            }

            composable(Routes.Notification.route) {
                Notification()
            }

            composable(Routes.Profile.route) {
                Profile(navHostController = navController)
            }
        }
    }
}

@Composable
fun MyBottomBar(navController1: NavHostController) {

    val backStackEntry = navController1.currentBackStackEntryAsState()

    val list = listOf(
        BottomNavItem(
            "Home",
            Routes.Home.route,
            Icons.Rounded.Home
        ),

        BottomNavItem(
            "Search",
            Routes.Search.route,
            Icons.Rounded.Search
        ),

        BottomNavItem(
            "Add Thread",
            Routes.AddThread.route,
            Icons.Rounded.Add
        ),

        BottomNavItem(
            "Notification",
            Routes.Notification.route,
            Icons.Rounded.Notifications
        ),

        BottomNavItem(
            "Profile",
            Routes.Profile.route,
            Icons.Rounded.Person
        )
    )

    BottomAppBar {

        list.forEach {
            val selected  = it.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController1.navigate(it.route){
                        popUpTo(navController1.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                }
            )
        }
    }

}
