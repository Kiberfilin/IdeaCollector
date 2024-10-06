package com.example.core_impl.clean.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_api.contracts.IdeasDatabaseContract
import com.example.core_api.database.IdeaRoomEntity

@Database(entities = [IdeaRoomEntity::class], version = 1)
abstract class IdeaDatabase : RoomDatabase(), IdeasDatabaseContract