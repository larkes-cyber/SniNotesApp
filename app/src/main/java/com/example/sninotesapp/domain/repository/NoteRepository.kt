package com.example.sninotesapp.domain.repository

import com.example.sninotesapp.data.database.entity.NoteEntity
import com.example.sninotesapp.domain.model.Note

interface NoteRepository {
    suspend fun insertNote(noteEntity: Note)
    suspend fun observeNotes():List<Note>
    suspend fun observeNoteById(id:Int): Note
    suspend fun deleteNote(noteEntity: Note)
}