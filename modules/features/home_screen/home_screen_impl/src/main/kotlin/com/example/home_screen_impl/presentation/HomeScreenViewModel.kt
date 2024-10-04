package com.example.home_screen_impl.presentation

import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel

class HomeScreenViewModel: BaseViewModel<HomeScreenRouter>() {
    fun handleOnTextClick() = router?.navigateToSettingsScreen()
}