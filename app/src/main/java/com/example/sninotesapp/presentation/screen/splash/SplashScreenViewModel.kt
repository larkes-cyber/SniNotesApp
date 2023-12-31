package com.example.sninotesapp.presentation.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sninotesapp.domain.repository.NoteRepository
import com.example.sninotesapp.domain.repository.UserRepository
import com.example.sninotesapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(SplashScreenState())
    val uiState:StateFlow<SplashScreenState> = _uiState

    init {
        viewModelScope.launch {
            val savedUserData = userRepository.getUserData()
            if(savedUserData == null) _uiState.value = SplashScreenState(status = Screen.LoginScreen.route)
            else {
                _uiState.value = SplashScreenState(isLoading = true)
                Log.d("sdfsdfsdfsdfsdf", noteRepository.notesSynchronization().message.toString())
                _uiState.value = SplashScreenState(status = Screen.NotesScreen.route)
            }
        }
    }

}