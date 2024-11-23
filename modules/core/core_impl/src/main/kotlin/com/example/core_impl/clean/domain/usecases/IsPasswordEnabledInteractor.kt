package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import com.example.core_api.constants.ENABLE_PASSWORD_KEY
import javax.inject.Inject

class IsPasswordEnabledInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : IsPasswordEnabledInputPort {
    override fun execute(): Boolean = repository.getBoolean(ENABLE_PASSWORD_KEY, false)
}