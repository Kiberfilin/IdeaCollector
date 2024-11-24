package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedThemeInputPort
import javax.inject.Inject

class GetPersistedThemeInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : GetPersistedThemeInputPort {
    override fun execute(key: String, defValue: String?): String? =
        repository.getString(key, defValue)
}