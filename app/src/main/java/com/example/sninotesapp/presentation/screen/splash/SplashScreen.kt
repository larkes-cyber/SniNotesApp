package com.example.sninotesapp.presentation.screen.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.sninotesapp.presentation.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState){
        if(uiState != null) navController.navigate(uiState!!)
    }
    Text(text = "splash")

}