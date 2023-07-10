package com.example.sninotesapp.domain.repository

import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User

interface UserRepository {

    fun putUserData(user: User)
    fun getUserData():User?
    suspend fun registerUser(login: Login):User

}