package com.example.sninotesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sninotesapp.data.database.dao.NoteDao
import com.example.sninotesapp.data.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDao
}