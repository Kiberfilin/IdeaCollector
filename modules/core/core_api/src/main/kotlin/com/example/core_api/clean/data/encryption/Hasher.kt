package com.example.core_api.clean.data.encryption

interface Hasher {
    fun md5(charSequence: CharSequence): String
}