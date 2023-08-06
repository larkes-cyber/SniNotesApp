package com.example.sninotesapp.domain.repository

import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.until.Resource

interface UserRepository {

    fun putUserData(user: User)
    fun getUserData():User?
    fun deleteUser()
    suspend fun registerUser(user: User): Resource<User>
    suspend fun observeUserData(session:String, email:String):Resource<User>
    suspend fun authUser(login: Login):Resource<String>

}