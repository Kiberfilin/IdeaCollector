package com.example.core_impl

import android.content.Context
import androidx.room.Room
import com.example.core_api.contracts.IdeasDatabaseContract
import com.example.core_api.database.IdeaDao
import com.example.core_api.di.qualifier.Application
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val IDEA_DATABASE_NAME = "IDEA_DATABASE"

@Module
class DatabaseModule {
    @Provides
    @Reusable
    fun provideIdeaDao(contract: IdeasDatabaseContract): IdeaDao = contract.ideaDao()

    @Provides
    @Singleton
    fun provideIdeaDatabase(@Application context: Context): IdeasDatabaseContract =
        Room.databaseBuilder(
            context,
            IdeaDatabase::class.java,
            IDEA_DATABASE_NAME
        ).build()
}