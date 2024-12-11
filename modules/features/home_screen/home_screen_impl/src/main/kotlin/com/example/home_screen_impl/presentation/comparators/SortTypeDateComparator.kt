package com.example.home_screen_impl.presentation.comparators

import com.example.core_api.clean.domain.entities.IdeaEntity
import javax.inject.Inject

class SortTypeDateComparator @Inject constructor() : Comparator<IdeaEntity> {
    override fun compare(idea1: IdeaEntity, idea2: IdeaEntity): Int =
        when {
            idea1.date < idea2.date -> -1
            idea1.date > idea2.date -> 1
            else -> 0
        }
}