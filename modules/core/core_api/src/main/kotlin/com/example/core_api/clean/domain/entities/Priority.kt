package com.example.core_api.clean.domain.entities

enum class Priority(val code: Int) {
    HIGH(0), MEDIUM(1), LOW(2), NONE(-1);

    companion object {
        fun map(code: Int): Priority {
            return when (code) {
                HIGH.code   -> HIGH
                MEDIUM.code -> MEDIUM
                LOW.code    -> LOW
                else        -> NONE
            }
        }
    }
}
