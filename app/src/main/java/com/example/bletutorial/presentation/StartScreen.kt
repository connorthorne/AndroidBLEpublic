package com.example.bletutorial.presentation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bletutorial.MapsActivity

@Composable
fun StartScreen(
    navController: NavController
) {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, MapsActivity::class.java))
    }) {

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.Blue, CircleShape)
                .clickable {
                    //Navigate
                    navController.navigate(Screen.SAFEButtonScreen.route) {
                        popUpTo(Screen.StartScreen.route) {
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Start",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(Color.Blue, CircleShape)
                .clickable {
                    //Navigate
                    navController.navigate(Screen.ContactScreen.route) {
                        popUpTo(Screen.ContactScreen.route) {
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contacts",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(Color.Blue, CircleShape)
                .clickable {
                    //Navigate
                    navController.navigate(Screen.MapScreen.route) {
                        popUpTo(Screen.MapScreen.route) {
                            inclusive = true
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Map",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}