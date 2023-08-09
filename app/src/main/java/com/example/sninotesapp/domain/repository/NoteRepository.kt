package com.example.sninotesapp.domain.repository

import com.example.sninotesapp.data.database.entity.NoteEntity
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.until.Resource

interface NoteRepository {
    suspend fun insertNote(noteEntity: Note)
    suspend fun observeNotes():List<Note>
    suspend fun observeNoteById(id:String): Note
    suspend fun deleteNote(noteEntity: Note)
    suspend fun pushNote(note: Note):Resource<String>
    suspend fun updateNote(note:Note):Resource<String>
    suspend fun noteSyncWithServer(note: Note):Resource<String>
    suspend fun notesSynchronization(): Resource<String>

}