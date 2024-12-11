package com.example.home_screen_impl.presentation.comparators

import com.example.core_api.clean.domain.entities.IdeaEntity
import javax.inject.Inject

class SortTypePriorityComparator @Inject constructor() : Comparator<IdeaEntity> {
    override fun compare(idea1: IdeaEntity, idea2: IdeaEntity): Int =
        idea1.priority.code - idea2.priority.code
}