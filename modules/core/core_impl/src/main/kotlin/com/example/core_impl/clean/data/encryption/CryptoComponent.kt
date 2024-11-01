package com.example.core_impl.clean.data.encryption

import com.example.core_api.contracts.AppProvider
import com.example.core_api.clean.data.encryption.SecurityProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CryptoModule::class],
    dependencies = [AppProvider::class]
)
interface CryptoComponent : SecurityProvider