package com.example.sninotesapp.data.remote.source

import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.until.Resource

interface UserRemoteDataSource {
    suspend fun registerUser(user: User):Resource<User>
    suspend fun authorizationUser(login: Login):Resource<String>
    suspend fun observeUser(session:String, email:String):Resource<User>
    companion object{
        const val BASE_URL = "http://192.168.43.34:8080/user"
    }

    sealed class Endpoints(val url: String){
        object Register: Endpoints("$BASE_URL/register")
        object Authorization: Endpoints("$BASE_URL/authorization")
    }
}