package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.remote.model.UserDataDto
import com.example.sninotesapp.domain.model.User

fun UserDataDto.toUser():User = User(
    name = name,
    password = password,
    login = login,
    session = session
)