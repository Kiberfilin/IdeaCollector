package com.example.core_impl.clean.data.repositories

import com.example.core_api.clean.data.DataProvider
import com.example.core_api.clean.data.database.DatabaseProvider
import com.example.core_api.clean.data.encryption.SecurityProvider
import com.example.core_api.contracts.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoriesModule::class],
    dependencies = [DatabaseProvider::class, SecurityProvider::class, AppProvider::class]
)
interface RepositoriesComponent : DataProvider