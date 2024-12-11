package com.example.home_screen_impl.presentation.comparators.factory

import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.constants.SORT_TYPE_DATE
import com.example.core_api.constants.SORT_TYPE_PRIORITY
import com.example.home_screen_impl.presentation.comparators.SortTypeDateComparator
import com.example.home_screen_impl.presentation.comparators.SortTypePriorityComparator
import javax.inject.Inject

class SortTypeComparatorFactory @Inject constructor(
    private val priorityComparator: SortTypePriorityComparator,
    private val dateComparator: SortTypeDateComparator
) {
    fun provideComparator(sortType: String): Comparator<IdeaEntity> =
        when (sortType) {
            SORT_TYPE_PRIORITY -> priorityComparator
            SORT_TYPE_DATE -> dateComparator
            else -> throw IllegalArgumentException("Wrong sort type: $sortType")
        }
}