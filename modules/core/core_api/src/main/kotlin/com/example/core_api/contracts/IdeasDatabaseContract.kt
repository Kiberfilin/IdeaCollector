package com.example.core_api.contracts

import com.example.core_api.clean.data.database.IdeaDao

interface IdeasDatabaseContract {
    fun ideaDao(): IdeaDao
}