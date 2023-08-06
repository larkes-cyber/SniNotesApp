package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.remote.model.UserDto
import com.example.sninotesapp.domain.model.User

fun User.toUserDto():UserDto{
    return UserDto(
        userName = name,
        email = login,
        password = password
    )
}
