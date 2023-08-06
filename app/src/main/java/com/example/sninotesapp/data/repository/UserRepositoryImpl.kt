package com.example.sninotesapp.data.repository

import com.example.sninotesapp.data.database.source.UserSharedPreferenceDataSource
import com.example.sninotesapp.data.remote.source.UserRemoteDataSource
import com.example.sninotesapp.domain.model.Login
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.domain.repository.UserRepository
import com.example.sninotesapp.until.Resource
import kotlin.math.log

class UserRepositoryImpl(
    private val userSharedPreferenceDataSource: UserSharedPreferenceDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
):UserRepository {

    override fun putUserData(user: User) = userSharedPreferenceDataSource.putUserData(user = user)
    override fun getUserData(): User? = userSharedPreferenceDataSource.getUserData()
    override fun deleteUser() = userSharedPreferenceDataSource.deleteUser()
    override suspend fun registerUser(user: User): Resource<User> = userRemoteDataSource.registerUser(user)
    override suspend fun observeUserData(session: String, email: String): Resource<User> = userRemoteDataSource.observeUser(session = session, email = email)
    override suspend fun authUser(login: Login): Resource<String> = userRemoteDataSource.authorizationUser(login)

}