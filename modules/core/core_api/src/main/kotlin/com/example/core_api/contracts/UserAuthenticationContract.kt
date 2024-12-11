package com.example.core_api.contracts

import kotlinx.coroutines.flow.MutableStateFlow

interface UserAuthenticationContract {
    fun getUserAuthenticatedStateFlow(): MutableStateFlow<Boolean>
}