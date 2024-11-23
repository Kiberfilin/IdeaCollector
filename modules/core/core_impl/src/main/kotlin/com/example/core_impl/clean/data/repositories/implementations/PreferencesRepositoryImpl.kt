package com.example.core_impl.clean.data.repositories.implementations

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.constants.THEME_KEY
import com.example.core_api.di.qualifier.Encrypted
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @Encrypted private val encryptedSharedPreferences: SharedPreferences,
    private val preferencesDataStore: DataStore<Preferences>
) : PreferencesRepositoryInputPort {

    override fun encryptedPersistString(key: String, value: String?) {
        encryptedSharedPreferences.edit().putString(key, value).apply()
    }

    override fun encryptedGetPersistedString(key: String, defValue: String?): String? {
        return encryptedSharedPreferences.getString(key, defValue)
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    override fun getThemePreferenceFlow(): Flow<String?> =
        preferencesDataStore.data.catch { exception ->
            Log.i("***", "exception is $exception")
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences: Preferences ->
            val value = preferences[PreferencesKeys.THEME_PREFERENCES_KEY].also {
                Log.i("***", "theme value = $it")
            }
         value
        }

    private object PreferencesKeys {
        val THEME_PREFERENCES_KEY: Preferences.Key<String> = stringPreferencesKey(THEME_KEY)
    }
}