package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.HashPasswordInputPort
import javax.inject.Inject

class HashPasswordInteractor @Inject constructor(
    private val repository: CryptoRepositoryInputPort
) : HashPasswordInputPort {
    override suspend fun execute(clearText: String): String =
        repository.hashPassword(clearText)
}