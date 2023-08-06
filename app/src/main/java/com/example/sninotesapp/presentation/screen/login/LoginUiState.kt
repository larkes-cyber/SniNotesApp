package com.example.sninotesapp.presentation.screen.login

import com.example.sninotesapp.until.Constants.RegistrationMode

data class LoginUiState(
  val mode:String = RegistrationMode,
  val emailTextFieldValue:String = "",
  val passwordTextFieldValue:String = "",
  val nameTextFieldValue:String = "",
  val hasBeenDone:Boolean = false,
  val error:String = ""
)