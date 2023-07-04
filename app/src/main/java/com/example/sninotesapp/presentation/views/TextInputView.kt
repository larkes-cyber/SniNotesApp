package com.example.sninotesapp.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sninotesapp.presentation.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun TextInputView(
    modifier: Modifier = Modifier,
    text:String,
    hint:String = "",
    placeholder:String = "",
    singleLine:Boolean = false,
    onChange:(String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }


    OutlinedTextField(
        value = text,
        onValueChange = {
            onChange(it)
        },
        placeholder = {
            Text(
            text = placeholder,
            color = AppTheme.colors.primaryTitleColor.copy(alpha = 0.6f)
              )
                      },
        label = { Text(
            text = hint,
            color = AppTheme.colors.primaryTitleColor.copy(alpha = 0.6f)
        ) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        delay(200)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = AppTheme.colors.primaryTitleColor,
            unfocusedIndicatorColor = AppTheme.colors.primaryTitleColor,
            textColor = AppTheme.colors.primaryTitleColor,
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(6.dp),
        singleLine = singleLine
    )
}