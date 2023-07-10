package com.example.sninotesapp.presentation.screen.splash

import androidx.lifecycle.ViewModel
import com.example.sninotesapp.domain.repository.UserRepository
import com.example.sninotesapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {

    private val _uiState = MutableStateFlow<String?>(null)
    val uiState:StateFlow<String?> = _uiState

    init {
        val savedUserData = userRepository.getUserData()
        if(savedUserData == null) _uiState.value = Screen.LoginScreen.route
        else _uiState.value = Screen.NotesScreen.route
    }

}