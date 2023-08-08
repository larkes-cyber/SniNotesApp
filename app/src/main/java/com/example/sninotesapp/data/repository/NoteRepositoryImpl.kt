package com.example.sninotesapp.data.repository

import android.util.Log
import com.example.sninotesapp.data.database.source.NoteDatabaseDataSource
import com.example.sninotesapp.data.database.source.UserSharedPreferenceDataSource
import com.example.sninotesapp.data.remote.source.NoteRemoteDataSource
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.mapper.toNote
import com.example.sninotesapp.domain.mapper.toNoteDto
import com.example.sninotesapp.domain.mapper.toNoteEntity
import com.example.sninotesapp.domain.repository.NoteRepository
import com.example.sninotesapp.until.Resource

class NoteRepositoryImpl(
    private val noteDatabaseDataSource: NoteDatabaseDataSource,
    private val noteRemoteDataSource: NoteRemoteDataSource,
    private val userSharedPreferenceDataSource: UserSharedPreferenceDataSource
):NoteRepository {
    override suspend fun insertNote(noteEntity: Note) =
        noteDatabaseDataSource.insertNote(noteEntity.toNoteEntity())

    override suspend fun observeNotes(): List<Note> =
        noteDatabaseDataSource.observeNotes().map { it.toNote() }

    override suspend fun observeNoteById(id: String): Note =
        noteDatabaseDataSource.observeNoteById(id).toNote()

    override suspend fun deleteNote(noteEntity: Note) {

        val serverResponse = noteRemoteDataSource.deleteNote(
            note = noteEntity.toNoteDto(),
            user = userSharedPreferenceDataSource.getUserData()!!
        )
        if(serverResponse.message != null){
            noteEntity.visible = false
            insertNote(noteEntity)
        }
        else noteDatabaseDataSource.deleteNote(noteEntity.toNoteEntity())

    }

    override suspend fun pushNote(note: Note): Resource<String> = noteRemoteDataSource.pushNote(
        note = note.toNoteDto(),
        user = userSharedPreferenceDataSource.getUserData()!!
    )

    override suspend fun updateNote(note: Note): Resource<String> = noteRemoteDataSource.updateNote(
        note = note.toNoteDto(),
        user = userSharedPreferenceDataSource.getUserData()!!
    )

    override suspend fun noteSyncWithServer(note: Note):Resource<String> {
         try {
            val findNoteRes = noteRemoteDataSource.findNote(note = note.toNoteDto(), user = userSharedPreferenceDataSource.getUserData()!!)
            if(findNoteRes.message == null) updateNote(note)
            else {
                val id = pushNote(note = note)
                if(note.id != null) noteDatabaseDataSource.deleteNote(note.toNoteEntity())
                return Resource.Success(id.data)
            }
            return Resource.Success("Updated")
        }catch (e:Exception){
            return Resource.Error(e.message.toString())
        }
    }
}
