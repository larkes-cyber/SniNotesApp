package com.example.sninotesapp.data.remote.source

import android.util.Log
import com.example.sninotesapp.data.remote.api.UserApi
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse


class UserRemoteDataSourceImpl(
    private val client: HttpClient,
    private val userApi: UserApi
): UserRemoteDataSource {
    override suspend fun registerUser(login: Login): User {
        Log.d("sdzfsdfsdf",UserRemoteDataSource.Endpoints.Register.url)
//        val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Register.url){
//            contentType(ContentType.Application.Json)
//            body = login.toLoginDto()
//        }
        val response:HttpResponse = client.get("http://192.168.0.100:8080/notes"){

        }
        Log.d("SDFSDFSDFSD",response.content.toString())
        return User("sdfsdf","sfdsdf","sfsdf","sdf")
    }
}