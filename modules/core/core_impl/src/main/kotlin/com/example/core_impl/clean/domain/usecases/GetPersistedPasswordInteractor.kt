package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import javax.inject.Inject

class GetPersistedPasswordInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : GetPersistedPasswordInputPort {
    override fun execute(key: String, defValue: String?): String? =
        repository.encryptedGetPersistedString(key, defValue)
}