package com.example.sninotesapp.data.remote.source

import com.example.sninotesapp.data.remote.model.NoteDto
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.until.Resource

interface NoteRemoteDataSource {

    suspend fun pushNote(note: NoteDto, user: User):Resource<String>

    companion object{
        const val BASE_URL = "http://192.168.43.34:8080"
    }

    sealed class Endpoints(val url: String){
        object Notes: Endpoints("$BASE_URL/notes")
        object SingleNote: Endpoints("$BASE_URL/singleNote")
    }

}