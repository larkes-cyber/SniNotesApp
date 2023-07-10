package com.example.sninotesapp.data.repository

import com.example.sninotesapp.data.database.source.UserSharedPreferenceDataSource
import com.example.sninotesapp.data.remote.source.UserRemoteDataSource
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.domain.repository.UserRepository
import kotlin.math.log

class UserRepositoryImpl(
    private val userSharedPreferenceDataSource: UserSharedPreferenceDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
):UserRepository {

    override fun putUserData(user: User) = userSharedPreferenceDataSource.putUserData(user = user)
    override fun getUserData(): User? = userSharedPreferenceDataSource.getUserData()
    override suspend fun registerUser(login: Login): User = userRemoteDataSource.registerUser(login)

}