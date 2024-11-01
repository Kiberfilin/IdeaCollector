package com.example.core_impl.clean.data.encryption

import com.example.core_api.clean.data.encryption.Hasher
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class HasherImpl @Inject constructor() : Hasher {
    override fun md5(charSequence: CharSequence): String =
        createHash(charSequence.toString(), MD5)

    private fun createHash(plaintext: String, type: String): String {
        val md = MessageDigest.getInstance(type)
        val bigInt = BigInteger(1, md.digest(plaintext.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    companion object {
        private const val MD5: String = "MD5"
    }
}