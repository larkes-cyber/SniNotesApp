package com.example.sninotesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sninotesapp.presentation.screen.login.LoginScreen
import com.example.sninotesapp.presentation.screen.login.LoginScreenViewModel
import com.example.sninotesapp.presentation.screen.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){

        composable(Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.NotesScreen.route){

        }
        composable(Screen.LoginScreen.route){
            val viewModel:LoginScreenViewModel = hiltViewModel()
            LoginScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.NoteDetailScreen.route){

        }

    }

}