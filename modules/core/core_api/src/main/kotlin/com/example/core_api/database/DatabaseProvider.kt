package com.example.core_api.database

import com.example.core_api.contracts.IdeasDatabaseContract

interface DatabaseProvider {
    fun provideDatabase(): IdeasDatabaseContract
    fun ideaDao(): IdeaDao
}