package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.SharedPreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetThemeInputPort
import com.example.core_api.constants.THEME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetThemeInteractor @Inject constructor(
    private val repository: SharedPreferencesRepositoryInputPort
) : GetThemeInputPort {
    override fun execute(): Flow<String?> = flow {
        emit(repository.getString(THEME_KEY, null))
    }
}