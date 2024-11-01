package com.example.core_impl.clean.data.encryption

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core_api.clean.data.encryption.Hasher
import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.di.qualifier.Application
import com.example.core_api.di.qualifier.Encrypted
import com.example.core_impl.clean.data.CryptoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton


@Module
interface CryptoModule {

    @Binds
    @Reusable
    fun bindsHasher(hasherImpl: HasherImpl): Hasher

    @Binds
    @Reusable
    fun bindsCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepositoryInputPort

    companion object {

        @Provides
        @Singleton
        fun provideMasterKey(@Application context: Context): MasterKey =
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        @Provides
        @Encrypted
        @Singleton
        fun provideEncryptedSharedPreferences(
            @Application context: Context,
            masterKey: MasterKey
        ): SharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        private const val PREFS_FILE_NAME: String = "com.example.ideacollector.PREFS_FILE_NAME"
    }
}