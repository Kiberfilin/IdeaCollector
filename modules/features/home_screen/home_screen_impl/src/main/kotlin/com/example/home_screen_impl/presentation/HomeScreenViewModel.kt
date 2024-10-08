package com.example.home_screen_impl.presentation

import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel

class HomeScreenViewModel(
    private val getIdeas: GetAllIdeasInputPort
): BaseViewModel<HomeScreenRouter>() {
    fun handleOnTextClick() = router?.navigateToSettingsScreen()
    fun getAllIdeas() = getIdeas.execute()
}