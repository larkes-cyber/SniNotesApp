package com.example.sninotesapp.data.repository

import com.example.sninotesapp.data.database.entity.NoteEntity
import com.example.sninotesapp.data.source.NoteDatabaseDataSource
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.model.toNote
import com.example.sninotesapp.domain.model.toNoteEntity
import com.example.sninotesapp.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDatabaseDataSource: NoteDatabaseDataSource
):NoteRepository {
    override suspend fun insertNote(noteEntity: Note) {
        noteDatabaseDataSource.insertNote(noteEntity.toNoteEntity())
    }

    override suspend fun observeNotes(): List<Note> = noteDatabaseDataSource.observeNotes().map { it.toNote() }

    override suspend fun observeNoteById(id: Int): Note = noteDatabaseDataSource.observeNoteById(id).toNote()
}