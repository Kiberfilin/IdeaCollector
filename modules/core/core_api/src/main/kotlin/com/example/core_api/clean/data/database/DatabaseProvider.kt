package com.example.core_api.clean.data.database

import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.contracts.IdeasDatabaseContract

interface DatabaseProvider {
    fun provideDatabase(): IdeasDatabaseContract
    fun ideaDao(): IdeaDao
    fun repository(): IdeasRepositoryInputPort
}