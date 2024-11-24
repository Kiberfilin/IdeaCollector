package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistThemeInputPort
import javax.inject.Inject

class PersistThemeInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : PersistThemeInputPort {
    /**
     * Из-за того, что в Preference никак не запихнуть DataStore<T>, то приходится сохранять
     * тему и в датастор, и в обычные SharedPreferences, чтобы выбранная тема в фрагменте под
     * свойством нормально отображалась
     */
    override suspend fun execute(key: String, value: String) =
        repository.run {
            persistString(key, value)
            persistTheme(value)
        }
}