package com.example.core_impl.clean.data.database

import android.content.Context
import androidx.room.Room
import com.example.core_api.clean.data.RoomDataStore
import com.example.core_api.contracts.IdeasDatabaseContract
import com.example.core_api.database.IdeaDao
import com.example.core_api.di.qualifier.Application
import com.example.core_impl.clean.data.RoomDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val IDEA_DATABASE_NAME = "IDEA_DATABASE"

@Module
interface DatabaseModule {
    @Binds
    @Reusable
    fun bindRoomDatastore(dataStore: RoomDataStoreImpl): RoomDataStore

    companion object {
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
}