package com.example.home_screen_impl.presentation.converters

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateFormatConverterForRecyclerView {
    @SuppressLint("SimpleDateFormat")
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    fun convertFromMillsToString(dateInMills: Long): String = dateFormat.format(Date(dateInMills))
    fun convertFromStringToMills(dateString: String): Long = dateFormat.parse(dateString)?.time
        ?: throw IllegalArgumentException(
            "Wrong string for date parsing, for correct parsing, need to use pattern: ${
                dateFormat.toPattern()
            }."
        )
}