package com.example.core_api.clean.data

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort

interface DataProvider {
    fun provideCryptoRepository(): CryptoRepositoryInputPort
    fun provideIdeasRepository(): IdeasRepositoryInputPort
    fun provideSharedPreferencesRepository(): PreferencesRepositoryInputPort
}