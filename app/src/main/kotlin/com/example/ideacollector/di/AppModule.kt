package com.example.ideacollector.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.PreferenceManager
import com.example.core_api.constants.DATASTORE_NAME
import com.example.core_api.constants.THEME_KEY
import com.example.core_api.di.qualifier.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideDefaultSharedPreferences(@Application context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providePreferencesDataStore(@Application context: Context): DataStore<Preferences> =
        context.datastore
}

private val Context.datastore by preferencesDataStore(
    name = DATASTORE_NAME,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context,
                "${context.packageName}_preferences",
                setOf(THEME_KEY)
            )
        )
    })
