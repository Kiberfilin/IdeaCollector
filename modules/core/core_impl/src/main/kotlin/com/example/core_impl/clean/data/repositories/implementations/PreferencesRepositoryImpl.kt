package com.example.core_impl.clean.data.repositories.implementations

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
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

    override fun persistString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend fun persistTheme(value: String) =
        preferencesDataStore.setValue(PreferencesKeys.THEME_PREFERENCES_KEY, value)

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

/***
 *
 * An Extension function to DataStore<Preference>.
 * Generic way to getting preference value from the data store.
 * Returns preference value for the @param key if found,
 * Otherwise it returns @param defValue
 *
 * @param key Preference key
 * @param defValue default value if the preference key is not found
 *
 * @return a Flow of type T
 */
private fun <T> DataStore<Preferences>.getValue(
    key: Preferences.Key<T>,
    defValue: T
): Flow<T> {
    return data.map { preferences ->
        preferences[key] ?: defValue
    }
}

/***
 *
 * An Extension function to DataStore<Preference>
 * Generic way to editing preference value from the data store.
 * Edit the value of the preference @param key with @param value.
 *
 * @param key Preference key
 * @param value Value need to be set to the given key
 *
 */
private suspend fun <T> DataStore<Preferences>.setValue(
    key: Preferences.Key<T>,
    value: T
) {
    edit { preferences ->
        preferences[key] = value
    }
}