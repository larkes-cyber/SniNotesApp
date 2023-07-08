package com.example.sninotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.sninotesapp.data.database.AppDatabase
import com.example.sninotesapp.data.database.dao.NoteDao
import com.example.sninotesapp.data.repository.NoteRepositoryImpl
import com.example.sninotesapp.data.source.NoteDatabaseDataSource
import com.example.sninotesapp.data.source.NoteDatabaseDataSourceImpl
import com.example.sninotesapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(@ApplicationContext appContext: Context):Context{
        return appContext
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,"sninotes_database"
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase) = database.noteDao()

    @Provides
    fun provideNoteDatabaseDataSource(
        noteDao: NoteDao
    ):NoteDatabaseDataSource = NoteDatabaseDataSourceImpl(noteDao = noteDao)

    @Provides
    fun provideNoteRepository(noteDatabaseDataSource: NoteDatabaseDataSource):NoteRepository{
        return NoteRepositoryImpl(noteDatabaseDataSource = noteDatabaseDataSource)
    }
}