package com.example.ideacollector

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.core_api.constants.THEME_KEY
import com.example.core_api.contracts.AppWithFacade
import com.example.core_api.contracts.ProvidersFacade
import com.example.ideacollector.di.FacadeComponent

class App : Application(), AppWithFacade {
  /*  private val PREFERENCES_NAME = "${packageName}_preferences"
    private val dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCES_NAME,
        produceMigrations = { context ->
            // Since we're migrating from SharedPreferences, add a migration based on the
            // SharedPreferences name
            listOf(SharedPreferencesMigration(context, PREFERENCES_NAME, setOf(THEME_KEY)))
        }
    )
*/
    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(applicationContext)
            .also { facadeComponent = it }
    }

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }
}