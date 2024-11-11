package com.example.home_screen_impl.presentation.state

import com.example.core_api.clean.domain.entities.IdeaEntity

data class HomeScreenState(val isLocked: Boolean, val entity: IdeaEntity)
