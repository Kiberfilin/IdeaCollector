package com.example.core_api.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas_table")
    fun ideas(): Flow<List<IdeaEntity>>
}
