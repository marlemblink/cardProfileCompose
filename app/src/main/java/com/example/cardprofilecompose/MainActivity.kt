package com.example.cardprofilecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardprofilecompose.ui.theme.CardProfileComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardProfileComposeTheme {
               // Surface(color = MaterialTheme.colorScheme.background) {
                    CreateCardProfile()
                //}
            }
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun CreateCardProfile() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.pinkBackground)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .height(800.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(image = R.drawable.logo)
                Divider(thickness = 5.dp)
                InfoProfile()
                Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(R.color.pink)
                ),
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                }
                ) {
                    Text(
                        text = "Members",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                if(buttonClickedState.value) {
                    ContentMembers()
                }  else {
                    Box { }
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentMembers() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Members(
                data = listOf(
                MemberInfo("Jisoo", "Singer, model and actress from Korea", R.drawable.jisoo),
                MemberInfo("Rose", "Singer, model and dancer from Korea and New Zealand", R.drawable.rose),
                MemberInfo("Jennie", "Singer, model, rapper and dancer from Korea", R.drawable.jennie),
                MemberInfo("Lisa", "Rapper, singer, dancer and model from Thailand", R.drawable.lisa)
            )
            )
        }
    }
}
@Composable
fun Members(data: List<MemberInfo>) {
    LazyColumn {
        items(data) {item ->
           Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(7.dp)) {
                    CreateImageProfile(
                        modifier = Modifier
                            .size(100.dp),
                        item.image
                    )
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.bio,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoProfile() {
    Column(
        modifier = Modifier
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Blackpink",
            style = MaterialTheme.typography.titleLarge,
            color = Color.DarkGray
        )
        Text(
            text = "Best kpop group",
            modifier = Modifier
                .padding(3.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = "@blackPinkOfficial",
            modifier = Modifier
                .padding(3.dp)
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier, image: Int) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "profile image",
            modifier = modifier
                .size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

