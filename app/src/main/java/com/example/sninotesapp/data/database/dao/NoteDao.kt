package com.example.sninotesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sninotesapp.data.database.entity.NoteEntity
import javax.inject.Inject

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    suspend fun observeNotes():List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun observeNoteById(id:String):NoteEntity

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

}