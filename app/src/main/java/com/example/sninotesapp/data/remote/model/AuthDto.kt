package com.example.sninotesapp.data.remote.model

@kotlinx.serialization.Serializable
data class LoginDto(
    val email:String,
    val password:String
)