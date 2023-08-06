package com.example.sninotesapp.data.repository

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
    override suspend fun insertNote(noteEntity: Note) = noteDatabaseDataSource.insertNote(noteEntity.toNoteEntity())
    override suspend fun observeNotes(): List<Note> = noteDatabaseDataSource.observeNotes().map { it.toNote() }
    override suspend fun observeNoteById(id: Int): Note = noteDatabaseDataSource.observeNoteById(id).toNote()
    override suspend fun deleteNote(noteEntity: Note)= noteDatabaseDataSource.deleteNote(noteEntity.toNoteEntity())
    override suspend fun pushNote(note: Note):Resource<String> = noteRemoteDataSource.pushNote(
        note = note.toNoteDto(),
        user = userSharedPreferenceDataSource.getUserData()!!
    )
}