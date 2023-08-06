package com.example.sninotesapp.data.database.source

import com.example.sninotesapp.domain.model.User

interface UserSharedPreferenceDataSource {

    fun putUserData(user: User)
    fun getUserData():User?
    fun deleteUser()

}