package com.example.tweetapp.navigation

sealed class Routes(val route: String) {

    object Home: Routes("home")
    object Notification: Routes("notification")
    object Profile: Routes("profile")
    object Search: Routes("search")
    object Splash: Routes("splash")
    object AddThread: Routes("add_thread")
    object BottomNav: Routes("bottom_nav")
    object Login: Routes("login")
    object Register: Routes("register")
    object OtherUser: Routes("other_users/{data}")

}