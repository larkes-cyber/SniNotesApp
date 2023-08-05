package com.example.sninotesapp.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("/notes")
    suspend fun getData():Response<String>

}