package com.example.tweetapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tweetapp.screens.AddThreads
import com.example.tweetapp.screens.BottomNav
import com.example.tweetapp.screens.Home
import com.example.tweetapp.screens.Login
import com.example.tweetapp.screens.Notification
import com.example.tweetapp.screens.OtherUser
import com.example.tweetapp.screens.Profile
import com.example.tweetapp.screens.Register
import com.example.tweetapp.screens.Search
import com.example.tweetapp.screens.Splash

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        composable(Routes.Splash.route) {
            Splash(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Routes.Home.route){
            Home(navHostController = navController)
        }

        composable(Routes.Search.route) {
            Search(
                navHostController = navController
            )
        }

        composable(Routes.AddThread.route) {
            AddThreads(navHostController = navController)
        }

        composable(Routes.Notification.route) {
            Notification()
        }

        composable(Routes.Profile.route) {
            Profile(navHostController = navController)
        }

        composable(Routes.BottomNav.route) {
            BottomNav(
                modifier = modifier,
                navController = navController
            )
        }

        composable(Routes.Login.route) {
            Login(navController = navController)
        }

        composable(Routes.Register.route) {
            Register(
                navController = navController
            )
        }

        composable(Routes.OtherUser.route) {
            val data  = it.arguments!!.getString("data")
            OtherUser(
                navHostController = navController,
                uid = data!!
            )
        }
    }
}