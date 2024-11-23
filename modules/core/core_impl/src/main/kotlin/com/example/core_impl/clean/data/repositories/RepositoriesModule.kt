package com.example.core_impl.clean.data.repositories

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_impl.clean.data.repositories.implementations.CryptoRepositoryImpl
import com.example.core_impl.clean.data.repositories.implementations.RoomIdeasRepository
import com.example.core_impl.clean.data.repositories.implementations.PreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoriesModule {
    @Binds
    @Reusable
    fun bindCryptoRepository(repository: CryptoRepositoryImpl): CryptoRepositoryInputPort

    @Binds
    @Reusable
    fun bindsSharedPreferencesRepository(
        repositoryImpl: PreferencesRepositoryImpl
    ): PreferencesRepositoryInputPort

    @Binds
    @Reusable
    fun bindRoomRepository(repository: RoomIdeasRepository): IdeasRepositoryInputPort
}