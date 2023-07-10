package com.example.sninotesapp.data.remote.source

import android.util.Log
import com.example.sninotesapp.domain.mapper.toLoginDto
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserRemoteDataSourceImpl(
    private val client: HttpClient
): UserRemoteDataSource {
    override suspend fun registerUser(login: Login): User {
        Log.d("sdzfsdfsdf",UserRemoteDataSource.Endpoints.Register.url)
        val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Register.url){
            contentType(ContentType.Application.Json)
            body = login.toLoginDto()
        }
        Log.d("SDFSDFSDFSD",response.toString())
        return User("sdfsdf","sfdsdf","sfsdf","sdf")
    }
}