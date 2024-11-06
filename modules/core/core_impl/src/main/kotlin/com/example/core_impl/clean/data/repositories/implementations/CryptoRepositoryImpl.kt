package com.example.core_impl.clean.data.repositories.implementations

import com.example.core_api.clean.data.encryption.Hasher
import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val hasher: Hasher
) : CryptoRepositoryInputPort {
    override suspend fun hashPassword(clearText: String): String =
        hasher.md5(clearText)
}