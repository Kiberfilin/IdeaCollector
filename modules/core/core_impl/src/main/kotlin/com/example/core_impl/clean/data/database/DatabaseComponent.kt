package com.example.core_impl.clean.data.database

import com.example.core_api.contracts.AppProvider
import com.example.core_api.database.DatabaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider