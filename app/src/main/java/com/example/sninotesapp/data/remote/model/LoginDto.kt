package com.example.sninotesapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email:String,
    var password:String,
    val userName:String = ""
)