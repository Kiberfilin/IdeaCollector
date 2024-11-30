package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.CheckIsPasswordCorrectInputPort
import com.example.core_api.constants.PASSWORD_AND_CONFIRMATION_KEY
import javax.inject.Inject

class CheckIsPasswordCorrectInteractor @Inject constructor(
    private val prefRepository: PreferencesRepositoryInputPort,
    private val cryptoRepository: CryptoRepositoryInputPort
) : CheckIsPasswordCorrectInputPort {
    override suspend fun execute(enteredPassword: String): Boolean {
        val persistedPassword: String = prefRepository.encryptedGetPersistedString(
            PASSWORD_AND_CONFIRMATION_KEY, ""
        ).toString()
        return if (persistedPassword.isNotEmpty()) {
            persistedPassword == cryptoRepository.hashPassword(enteredPassword)
        } else false
    }
}