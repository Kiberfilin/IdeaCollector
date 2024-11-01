package com.example.settings_impl.presentation.custom

import androidx.preference.PreferenceDataStore

class PasswordDatastore(
    private val persistString: (String, String?) -> Unit,
    private val extractPersistedString: (String, String?) -> String
) : PreferenceDataStore() {
    override fun putString(key: String, value: String?) = persistString.invoke(key, value)

    override fun getString(key: String, defValue: String?): String =
        extractPersistedString.invoke(key, defValue)
}