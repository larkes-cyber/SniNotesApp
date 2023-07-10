package com.example.sninotesapp.data.source

import com.example.sninotesapp.data.database.entity.NoteEntity

interface NoteDatabaseDataSource {

    suspend fun insertNote(noteEntity: NoteEntity)
    suspend fun observeNotes():List<NoteEntity>
    suspend fun observeNoteById(id:Int):NoteEntity
    suspend fun deleteNote(noteEntity: NoteEntity)
}