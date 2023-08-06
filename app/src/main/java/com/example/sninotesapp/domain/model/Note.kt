package com.example.sninotesapp.domain.model

import androidx.compose.ui.graphics.Color

data class Note(
    val id:String? = null,
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean
){
    companion object {
        private val colors = listOf(0xFFD0BCFF, 0xFFCCC2DC, 0xFFEFB8C8, 0xFFF2B8B5, 0xFFCAC5CD)
        fun generateRandom():Long{
            return colors.random()
        }
    }
}