package com.example.tweetapp.item_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.tweetapp.model.UserModel
import com.example.tweetapp.navigation.Routes

@Composable
fun UserItem(
    users: UserModel,
    navHostController: NavHostController,
) {

    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    val routes = Routes.OtherUser.route.replace("{data}", users.uid)
                    navHostController.navigate(routes)
                }
        ) {
            val (userImage, userName, date, time, title) = createRefs()


            Image(
                painter = rememberAsyncImagePainter(model = users.image),
                contentDescription = "close",
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .size(35.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )


            Text(
                text = users.userName,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .constrainAs(userName){
                        top.linkTo(userImage.top)
                        start.linkTo(userImage.end, margin = 12.dp)
                        bottom.linkTo(userImage.bottom)
                    }
            )

            Text(
                text = users.name,
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .constrainAs(title){
                        top.linkTo(userName.bottom, margin = 2.dp)
                        start.linkTo(userName.start)
                    }
            )
        }

        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }

}

@Preview(showBackground = true)
@Composable
fun UserItemPreview(){
//    UserItem()
}
