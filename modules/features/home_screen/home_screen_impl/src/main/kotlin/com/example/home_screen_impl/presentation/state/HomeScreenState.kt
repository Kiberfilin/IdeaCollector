package com.example.home_screen_impl.presentation.state

import com.example.core_api.clean.domain.entities.IdeaEntity

sealed class HomeScreenState {
    object Locked: HomeScreenState()
    data class Unlocked(
        val entity: IdeaEntity
    ): HomeScreenState()
}