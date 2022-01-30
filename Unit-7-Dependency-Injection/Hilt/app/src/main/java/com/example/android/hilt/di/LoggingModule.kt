package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class InMemoryLogger
@Qualifier annotation class DatabaseLogger

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseLoggingModule {
    @Singleton @Binds @DatabaseLogger
    abstract fun bindDatabaseLogger(implementation : LoggerLocalDataSource) : LoggerDataSource
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoggingInMemoryModule {
    @ActivityScoped @Binds @InMemoryLogger
    abstract fun bindInMemoryLogger(implementation : LoggerInMemoryDataSource) : LoggerDataSource
}