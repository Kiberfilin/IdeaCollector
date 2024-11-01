package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import javax.inject.Inject

class PersistPasswordInteractor @Inject constructor(
    private val repository: CryptoRepositoryInputPort
) : PersistPasswordInputPort {
    override fun execute(key: String, value: String?) =
        repository.persistString(key, value)
}