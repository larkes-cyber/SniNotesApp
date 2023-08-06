package com.example.sninotesapp.domain.mapper

import com.example.sninotesapp.data.remote.model.LoginDto
import com.example.sninotesapp.domain.model.Login

fun Login.toLoginDto():LoginDto = LoginDto(
    password = password,
    email = login
)
