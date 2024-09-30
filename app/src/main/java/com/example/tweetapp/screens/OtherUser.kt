package com.example.tweetapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.tweetapp.item_view.ThreadItem
import com.example.tweetapp.model.UserModel
import com.example.tweetapp.navigation.Routes
import com.example.tweetapp.utils.SharedPref
import com.example.tweetapp.viewModel.AuthViewModel
import com.example.tweetapp.viewModel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun OtherUser(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    uid: String
) {

    val context = LocalContext.current

    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState( null)

    val userViewModel: UserViewModel = viewModel()
    val thread by userViewModel.theads.observeAsState(null)
    val users by userViewModel.users.observeAsState(null)

    userViewModel.fetchThreads(uid)
    userViewModel.fetchUser(uid)

    LaunchedEffect(firebaseUser) {
        if (firebaseUser == null){
            navHostController.navigate(Routes.Login.route){
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    LazyColumn {
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val (text, logo, userName, button,
                    bio, followers, followings) = createRefs()

                Text(
                    text = users!!.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp),
                    modifier = Modifier
                        .constrainAs(text){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )

                Image(
                    painter = rememberAsyncImagePainter(model = users!!.image),
                    contentDescription = "close",
                    modifier = Modifier
                        .constrainAs(logo) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )


                Text(
                    text = users!!.userName,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .constrainAs(userName){
                            top.linkTo(text.bottom)
                            start.linkTo(parent.start)
                        }
                )


                Text(
                    text = users!!.bio,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .constrainAs(bio){
                            top.linkTo(userName.bottom)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = "0 Followers",
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .constrainAs(followers){
                            top.linkTo(bio.bottom)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = "0 Followings",
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .constrainAs(followings){
                            top.linkTo(followers.bottom)
                            start.linkTo(parent.start)
                        }
                )

                ElevatedButton(
                    onClick = {},
                    modifier = Modifier
                        .constrainAs(button){
                            top.linkTo(followings.bottom)
                            start.linkTo(parent.start)
                        }
                ) {
                    Text(text = "Follow")
                }
            }
        }

        if (thread != null && users != null){
            items(thread ?: emptyList()){ pair ->
                ThreadItem(
                    thread = pair,
                    users = users!!,
                    navHostController = navHostController,
                    userId = SharedPref.getUserName(context)
                )

            }
        }
    }
}