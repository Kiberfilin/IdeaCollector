package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.PreferencesRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetSortOrderInputPort
import com.example.core_api.constants.SORT_TYPE_KEY
import com.example.core_api.constants.SORT_TYPE_PRIORITY
import javax.inject.Inject

class GetSortOrderInteractor @Inject constructor(
    private val repository: PreferencesRepositoryInputPort
) : GetSortOrderInputPort {
    override fun execute(): String = repository.getString(SORT_TYPE_KEY, SORT_TYPE_PRIORITY)
        ?: SORT_TYPE_PRIORITY
}