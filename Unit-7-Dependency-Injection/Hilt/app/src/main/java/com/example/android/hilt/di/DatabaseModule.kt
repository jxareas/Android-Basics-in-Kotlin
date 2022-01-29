package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideLogDao(database : AppDatabase) = database.logDao()

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context : Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "logging.db").build()
}