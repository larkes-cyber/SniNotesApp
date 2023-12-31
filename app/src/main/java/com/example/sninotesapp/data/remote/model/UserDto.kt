package com.example.sninotesapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val email:String,
    var password:String,
    val userName:String = ""
)