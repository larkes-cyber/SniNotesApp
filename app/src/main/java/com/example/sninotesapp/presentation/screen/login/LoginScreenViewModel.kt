package com.example.sninotesapp.presentation.screen.login

import androidx.lifecycle.ViewModel
import com.example.sninotesapp.until.Constants.AuthorizationMode
import com.example.sninotesapp.until.Constants.RegistrationMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor():ViewModel() {

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
            _uiState.value = LoginUiState(mode = AuthorizationMode)
        }else{
            _uiState.value = LoginUiState(mode = RegistrationMode)
        }
    }
}