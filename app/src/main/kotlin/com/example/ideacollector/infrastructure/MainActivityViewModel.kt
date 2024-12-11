package com.example.ideacollector.infrastructure

import androidx.lifecycle.ViewModel
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel(
    isPasswordEnabled: IsPasswordEnabledInputPort,
) : ViewModel() {
    private val _isUserAuthenticated: MutableStateFlow<Boolean> =
        MutableStateFlow(!isPasswordEnabled.execute())

    fun getUserAuthStateFlow(): MutableStateFlow<Boolean> = _isUserAuthenticated
}