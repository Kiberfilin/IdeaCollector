package com.example.core_api.clean.data.encryption

import android.content.SharedPreferences
import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.di.qualifier.Encrypted

interface SecurityProvider {
    @Encrypted
    fun provideEncryptedSharedPreferences(): SharedPreferences
    fun provideHasher(): Hasher
    fun provideCryptoRepository(): CryptoRepositoryInputPort
}