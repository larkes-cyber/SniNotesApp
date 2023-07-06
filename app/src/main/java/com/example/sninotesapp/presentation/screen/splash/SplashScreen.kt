package com.example.sninotesapp.presentation.screen.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.sninotesapp.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit){
        navController.navigate(Screen.NotesScreen.route)
    }
    Text(text = "splash")

}