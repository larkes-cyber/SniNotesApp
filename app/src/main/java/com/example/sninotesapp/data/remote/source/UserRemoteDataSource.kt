package com.example.sninotesapp.data.remote.source

import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User

interface UserRemoteDataSource {
    suspend fun registerUser(login: Login):User
    companion object{
        const val BASE_URL = "http://0.0.0.0:8080/user"
    }

    sealed class Endpoints(val url: String){
        object Register: Endpoints("$BASE_URL/register")
        object Authorization: Endpoints("$BASE_URL/authorization")
    }
}