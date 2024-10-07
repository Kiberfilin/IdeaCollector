package com.example.core_api.clean.data.database

import com.example.core_api.clean.data.IdeasRepository
import com.example.core_api.contracts.IdeasDatabaseContract

interface DatabaseProvider {
    fun provideDatabase(): IdeasDatabaseContract
    fun ideaDao(): IdeaDao
    fun repository(): IdeasRepository
}