package com.example.sninotesapp.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.repository.UserRepository
import com.example.sninotesapp.until.Constants.AuthorizationMode
import com.example.sninotesapp.until.Constants.RegistrationMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState:StateFlow<LoginUiState> = _uiState



    fun onEmailTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(emailTextFieldValue = text)
    }

    fun onNameTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(nameTextFieldValue = text)
    }
    fun onPasswordTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(passwordTextFieldValue = text)
    }
    fun switchMode(){
        if(_uiState.value.mode == RegistrationMode){
            _uiState.value = uiState.value.copy(mode = AuthorizationMode)
        }else{
            _uiState.value = uiState.value.copy(mode = RegistrationMode)
        }
    }

    fun onSubmit(){
        viewModelScope.launch {
            userRepository.registerUser(Login(
                login = uiState.value.emailTextFieldValue,
                password = uiState.value.passwordTextFieldValue,
                name = uiState.value.nameTextFieldValue
            ))
        }
    }
}