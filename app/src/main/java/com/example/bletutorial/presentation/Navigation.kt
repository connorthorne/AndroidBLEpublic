package com.example.bletutorial.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable

fun Navigation (
    onBluetoothStateChanged:()->Unit
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route){
        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

        composable(Screen.SAFEButtonScreen.route){
            SAFEButtonScreen(
                onBluetoothStateChanged,
                navController = navController
            )
        }
        composable(Screen.ContactScreen.route){
            ContactScreen(navController = navController)
        }
        composable(Screen.MapScreen.route){
            MapScreen(navController = navController)
        }
    }
}

sealed class Screen(val route:String){
    object StartScreen:Screen("start_screen")
    object SAFEButtonScreen:Screen("safe_button_screen")
    object ContactScreen:Screen("contact_screen")
    object MapScreen:Screen("map_screen")
}