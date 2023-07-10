package com.example.sninotesapp.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sninotesapp.presentation.theme.AppTheme
import com.example.sninotesapp.presentation.views.PasswordInputView
import com.example.sninotesapp.presentation.views.NotesPrimaryButton
import com.example.sninotesapp.presentation.views.LoginInputView
import com.example.sninotesapp.until.Constants.RegistrationMode
import com.example.sninotesapp.until.Constants.SignInSubtitle
import com.example.sninotesapp.until.Constants.SignInTitle
import com.example.sninotesapp.until.Constants.SignUpSubtitle
import com.example.sninotesapp.until.Constants.SignUpTitle

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if(uiState.mode == RegistrationMode) SignUpTitle else SignInTitle,
            style = MaterialTheme.typography.h4,
            color = AppTheme.colors.primaryTitleColor
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = if(uiState.mode == RegistrationMode) SignUpSubtitle else SignInSubtitle,
            style = MaterialTheme.typography.h6,
            color = AppTheme.colors.primarySubtitleColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(55.dp))
        Column(
            modifier = Modifier.padding(horizontal = 26.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            if(uiState.mode == RegistrationMode) {
                LoginInputView(
                    text = uiState.nameTextFieldValue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(66.dp),
                    hint = "Enter your name",
                    onChange = {
                        viewModel.onNameTextFieldChange(it)
                    })
            }
            LoginInputView(
                text = uiState.emailTextFieldValue,
                hint = "Enter your email",
                modifier = Modifier
                    .fillMaxWidth(),
                onChange = {
                    viewModel.onEmailTextFieldChange(it)
                })
            PasswordInputView(text = uiState.passwordTextFieldValue, modifier = Modifier.fillMaxWidth()){
                viewModel.onPasswordTextFieldChange(it)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(Modifier.padding(horizontal = 26.dp)) {
            NotesPrimaryButton(text  = if(uiState.mode == RegistrationMode) "Sign Up" else "Sign In", modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)) {
                    viewModel.onSubmit()
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Row {
            Text(
                text = if(uiState.mode == RegistrationMode) "Already have an account" else "Don't have an account",
                style = MaterialTheme.typography.body2,
                color = AppTheme.colors.primarySubtitleColor
            )
            Spacer(modifier = Modifier.width(5.dp))
            ClickableText(
                text = AnnotatedString(if(uiState.mode == RegistrationMode) "Sign in" else "Sign Up"),
                onClick = {viewModel.switchMode()},
                style = MaterialTheme.typography.body2.copy(color = AppTheme.colors.primary)
            )
        }

    }
    
}