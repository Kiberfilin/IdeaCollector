package com.example.core_api.contracts

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.example.core_api.di.qualifier.Application
import androidx.datastore.preferences.core.Preferences

interface AppProvider {
    @Application
    fun provideContext(): Context
    fun provideSharedPreferences(): SharedPreferences
    fun providePreferencesDataStore(): DataStore<Preferences>
}