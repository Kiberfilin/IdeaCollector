package com.example.core_impl.clean.domain.usecases

import android.util.Log
import com.example.core_api.clean.domain.boundaries.repository.SharedPreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import com.example.core_api.constants.ENABLE_PASSWORD_KEY
import javax.inject.Inject

class IsPasswordEnabledInteractor @Inject constructor(
    private val repository: SharedPreferencesRepositoryInputPort
) : IsPasswordEnabledInputPort {
    override fun execute(): Boolean {
        val isPasswordEnabled = repository.getBoolean(ENABLE_PASSWORD_KEY, false)
        Log.i("***", "isPasswordEnabled = $isPasswordEnabled")
        return isPasswordEnabled
    }
}