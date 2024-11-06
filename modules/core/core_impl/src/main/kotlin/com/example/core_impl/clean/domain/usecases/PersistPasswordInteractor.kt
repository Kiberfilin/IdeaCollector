package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.SharedPreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import javax.inject.Inject

class PersistPasswordInteractor @Inject constructor(
    private val repository: SharedPreferencesRepositoryInputPort
) : PersistPasswordInputPort {
    override fun execute(key: String, value: String?) =
        repository.encryptedPersistString(key, value)
}