package com.example.home_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(
    private val getIdeas: GetAllIdeasInputPort
): BaseViewModel<HomeScreenRouter>() {
    fun handleOnTextClick() = router?.navigateToSettingsScreen()
    suspend fun ideas(): StateFlow<List<IdeaEntity>> = getIdeas.execute().stateIn(viewModelScope)
}