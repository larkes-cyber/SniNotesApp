package com.example.sninotesapp.domain.model

data class Note(
    val id:Int? = null,
    val title:String,
    val text:String,
    val color:Long,
    val online_sync:Boolean
)