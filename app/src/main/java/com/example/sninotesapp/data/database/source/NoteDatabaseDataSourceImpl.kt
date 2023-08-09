package com.example.sninotesapp.data.database.source

import android.util.Log
import com.example.sninotesapp.data.database.dao.NoteDao
import com.example.sninotesapp.data.database.entity.NoteEntity

class NoteDatabaseDataSourceImpl(
    private val noteDao: NoteDao
): NoteDatabaseDataSource {
    override suspend fun insertNote(noteEntity: NoteEntity) = noteDao.insertNote(noteEntity)
    override suspend fun observeNotes(): List<NoteEntity> = noteDao.observeNotes()
    override suspend fun observeNoteById(id: String): NoteEntity = noteDao.observeNoteById(id)
    override suspend fun deleteNote(noteEntity: NoteEntity) = noteDao.deleteNote(noteEntity)
    override suspend fun nukeNotes() = noteDao.nukeNotes()
}