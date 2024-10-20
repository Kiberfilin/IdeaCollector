package com.example.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_api.contracts.IdeasDatabaseContract
import com.example.core_api.database.IdeaEntity

@Database(entities = [IdeaEntity::class], version = 1)
abstract class IdeaDatabase : RoomDatabase(), IdeasDatabaseContract