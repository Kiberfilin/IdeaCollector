package com.example.core_impl.clean.data.repositories

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.SharedPreferencesRepositoryInputPort
import com.example.core_impl.clean.data.repositories.implementations.CryptoRepositoryImpl
import com.example.core_impl.clean.data.repositories.implementations.RoomIdeasRepository
import com.example.core_impl.clean.data.repositories.implementations.SharedPreferencesRepositoryImpl
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
        repositoryImpl: SharedPreferencesRepositoryImpl
    ): SharedPreferencesRepositoryInputPort

    @Binds
    @Reusable
    fun bindRoomRepository(repository: RoomIdeasRepository): IdeasRepositoryInputPort
}