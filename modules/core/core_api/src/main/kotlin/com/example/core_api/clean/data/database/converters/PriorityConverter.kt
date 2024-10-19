package com.example.core_api.clean.data.database.converters

import androidx.room.TypeConverter
import com.example.core_api.clean.domain.entities.Priority

class PriorityConverter {
    @TypeConverter
    fun enumToInt(priority: Priority): Int = priority.code

    @TypeConverter
    fun intToEnum(code: Int): Priority = Priority.map(code)
}