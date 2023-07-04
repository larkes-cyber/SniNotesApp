package com.example.sninotesapp.presentation.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sninotesapp.presentation.theme.AppTheme

@Composable
fun TextFieldButton(
    modifier:Modifier = Modifier,
    text:String = "",
    onClick:() -> Unit
) {

    Button(
        onClick = {
            onClick()
         },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(11.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = AppTheme.colors.secondPrimaryTitleColor
        )
    }

}