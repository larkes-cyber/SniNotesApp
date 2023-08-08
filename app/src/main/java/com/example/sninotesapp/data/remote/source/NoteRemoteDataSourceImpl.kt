package com.example.sninotesapp.data.remote.source

import android.util.Log
import com.example.sninotesapp.data.remote.model.NoteDto
import com.example.sninotesapp.domain.mapper.toNoteDto
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.model.User
import com.example.sninotesapp.until.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class NoteRemoteDataSourceImpl(
    private val client: HttpClient
):NoteRemoteDataSource {
    override suspend fun pushNote(note: NoteDto, user: User): Resource<String> {

        return try {
            Log.d("dfsdfsdfsfd", "e.message.toString()")
            val response: HttpResponse = client.post(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
                body = note
            }
            Resource.Success(response.readText())
        }catch (e:Exception) {
            Log.d("dfsdfsdfsfd", e.message.toString())
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun updateNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response: HttpResponse = client.put(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
                body = note
            }
            Resource.Success("ok")
        }catch (e:Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun deleteNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response:HttpResponse = client.delete(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("id", note.id!!)
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
            }
            Log.d("sdfsdfsdf","Success")
            Resource.Success("Ok")
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun findNote(note: NoteDto, user: User): Resource<String> {
        return try {
            val response:HttpResponse = client.get(NoteRemoteDataSource.Endpoints.SingleNote.url){
                url {
                    parameters.append("id", note.id ?: "")
                    parameters.append("session", user.session)
                    parameters.append("email", user.login)
                }
                contentType(ContentType.Application.Json)
            }
            Resource.Success("Ok")
        }catch (e:Exception){
            Log.d("fdsdfsdfsdf",e.toString())
            Resource.Error(e.message.toString())
        }
    }
}