package com.example.sninotesapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = Colors(
    primary = Color(0xFF6750A4),
    primaryTitleColor = Color(0xFF1D1B20),
    primaryBackground =  Color(0xFFF6EDFF),
    primarySubtitleColor = Color(0xFF49454F),
)

private val LightColorPalette = Colors(
    primary = Color(0xFF6750A4),
    primaryTitleColor = Color(0xFF1D1B20),
    primaryBackground =  Color(0xFFF6EDFF),
    primarySubtitleColor = Color(0xFF49454F),
)

data class Colors(
    val primaryBackground:Color,
    val primaryTitleColor:Color,
    val primarySubtitleColor:Color,
    val primary:Color
)

@Composable
fun SniNotesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }



    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}