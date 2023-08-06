package com.example.sninotesapp.data.remote.source

import android.util.Log
import com.example.sninotesapp.data.remote.model.UserDataDto
import com.example.sninotesapp.data.remote.model.UserDto
import com.example.sninotesapp.domain.mapper.toLoginDto
import com.example.sninotesapp.domain.mapper.toUser
import com.example.sninotesapp.domain.mapper.toUserDto
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.until.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString


class UserRemoteDataSourceImpl(
    private val client: HttpClient
): UserRemoteDataSource {
    override suspend fun registerUser(user: User): Resource<User> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Register.url){
                contentType(ContentType.Application.Json)
                body = user.toUserDto()
            }
            val session = response.readText()
            Resource.Success(User(
                name = user.name,
                password = user.password,
                session = session,
                login = user.login
            ))
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun authorizationUser(login: Login): Resource<String> {
        return try {
            val response:HttpResponse = client.post(UserRemoteDataSource.Endpoints.Authorization.url){
                contentType(ContentType.Application.Json)
                body = login.toLoginDto()
            }
            val session = response.readText()
            Log.d("erfgfd", session)
            Resource.Success(session)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun observeUser(session: String, email: String): Resource<User> {
        return try {
            val response:HttpResponse = client.get(UserRemoteDataSource.BASE_URL){
                url{
                    parameters.append("session", session)
                    parameters.append("email", email)
                }
            }
            val userDto = Json.decodeFromString<UserDataDto>(response.readText())
            Resource.Success(userDto.toUser())
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }


}