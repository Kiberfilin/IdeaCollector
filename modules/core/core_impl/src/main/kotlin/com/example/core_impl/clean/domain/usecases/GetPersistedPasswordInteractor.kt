package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import javax.inject.Inject

class GetPersistedPasswordInteractor @Inject constructor(
    private val repository: CryptoRepositoryInputPort
) : GetPersistedPasswordInputPort {
    override fun execute(key: String, defValue: String?): String? =
        repository.getPersistedString(key, defValue)
}