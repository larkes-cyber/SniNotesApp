package com.example.sninotesapp.presentation.screen.notes

import com.example.sninotesapp.domain.model.User

data class UserUiState(
    val user:User? = null,
    val isLoading:Boolean = false,
    val error:String = "",
    val hasBeenQuit:Boolean = false
)