package com.example.core_api.clean.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {
    @Query("SELECT * FROM ideas_table")
    fun ideas(): Flow<List<IdeaRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdea(ideaRoomEntity: IdeaRoomEntity)

    @Update
    suspend fun updateIdea(ideaRoomEntity: IdeaRoomEntity)

    @Delete
    suspend fun deleteIdea(ideaRoomEntity: IdeaRoomEntity)
}
