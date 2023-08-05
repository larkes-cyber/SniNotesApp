package com.example.sninotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.sninotesapp.data.database.AppDatabase
import com.example.sninotesapp.data.database.dao.NoteDao
import com.example.sninotesapp.data.repository.NoteRepositoryImpl
import com.example.sninotesapp.data.repository.UserRepositoryImpl
import com.example.sninotesapp.data.database.source.NoteDatabaseDataSource
import com.example.sninotesapp.data.database.source.NoteDatabaseDataSourceImpl
import com.example.sninotesapp.data.database.source.UserSharedPreferenceDataSource
import com.example.sninotesapp.data.database.source.UserSharedPreferenceDataSourceImpl
import com.example.sninotesapp.data.remote.api.UserApi
import com.example.sninotesapp.data.remote.source.UserRemoteDataSource
import com.example.sninotesapp.data.remote.source.UserRemoteDataSourceImpl
import com.example.sninotesapp.domain.repository.NoteRepository
import com.example.sninotesapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(@ApplicationContext appContext: Context):Context{
        return appContext
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideRetrofitClient():Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.100:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserApi(retrofit: Retrofit):UserApi = retrofit.create(UserApi::class.java)

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
    ): NoteDatabaseDataSource = NoteDatabaseDataSourceImpl(noteDao = noteDao)

    @Provides
    fun provideNoteRepository(noteDatabaseDataSource: NoteDatabaseDataSource):NoteRepository{
        return NoteRepositoryImpl(noteDatabaseDataSource = noteDatabaseDataSource)
    }

    @Provides
    fun provideUserSharedPreferenceDataSource(context: Context): UserSharedPreferenceDataSource = UserSharedPreferenceDataSourceImpl(context = context)

    @Provides
    fun provideUserRemoteDataSource(client: HttpClient, userApi: UserApi): UserRemoteDataSource = UserRemoteDataSourceImpl(client = client, userApi)

    @Provides
    fun provideUserRepository(
        userSharedPreferenceDataSource: UserSharedPreferenceDataSource,
        userRemoteDataSource: UserRemoteDataSource
    ):UserRepository = UserRepositoryImpl(
        userSharedPreferenceDataSource = userSharedPreferenceDataSource,
        userRemoteDataSource = userRemoteDataSource
    )
}