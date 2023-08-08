package com.example.sninotesapp.data.remote.model

@kotlinx.serialization.Serializable
data class NoteDto(
    val title:String,
    val text:String,
    val id:String?,
    val color:Long = 1
)