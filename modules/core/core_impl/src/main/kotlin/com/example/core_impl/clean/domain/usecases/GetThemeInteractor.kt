package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetThemeInputPort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : GetThemeInputPort {
    override fun execute(): Flow<String?> = repository.getThemePreferenceFlow()
}