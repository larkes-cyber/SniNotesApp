package com.example.sninotesapp.presentation.navigation

sealed class Screen(val route:String){

    object LoginScreen:Screen("login")
    object SplashScreen:Screen("splash")
    object NotesScreen:Screen("notes")
    object NoteDetailScreen:Screen("note_detail")


}