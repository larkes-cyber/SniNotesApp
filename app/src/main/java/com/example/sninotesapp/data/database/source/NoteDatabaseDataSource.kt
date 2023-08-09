package com.example.sninotesapp.data.database.source

import com.example.sninotesapp.data.database.entity.NoteEntity

interface NoteDatabaseDataSource {

    suspend fun insertNote(noteEntity: NoteEntity)
    suspend fun observeNotes():List<NoteEntity>
    suspend fun observeNoteById(id:String):NoteEntity
    suspend fun deleteNote(noteEntity: NoteEntity)
    suspend fun nukeNotes()
}