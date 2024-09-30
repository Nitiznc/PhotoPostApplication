package com.example.tweetapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tweetapp.R
import com.example.tweetapp.navigation.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (image) = createRefs()

        Image(painter = painterResource(
            id = R.drawable.thread_logo),
            contentDescription = "Thread Logo",
            modifier = Modifier.constrainAs(image){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.size(150.dp)
        )
    }

    LaunchedEffect(true) {
        delay(2000)

        if (FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(Routes.BottomNav.route){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }else{
            navController.navigate(Routes.Login.route){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    
}