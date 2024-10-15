package com.example.home_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import com.example.ui_kit.custom_views.PRIORITY_GREEN
import com.example.ui_kit.custom_views.PRIORITY_RED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeScreenViewModel(
    private val getIdeas: GetAllIdeasInputPort,
    private val insertIdea: InsertIdeaInputPort,
    private val deleteIdea: DeleteIdeaInputPort,
    private val updateIdea: UpdateIdeaInputPort
) : BaseViewModel<HomeScreenRouter>() {
    companion object {
        const val EMPTY_STRING: String = ""
    }

    private val initialIdeaEntity =
        IdeaEntity(id = 0, ideaText = EMPTY_STRING, date = currentDate(), PRIORITY_RED)

    //fun handleOnTextClick() = router?.navigateToSettingsScreen()
    suspend fun ideas(): StateFlow<List<IdeaEntity>> = getIdeas.execute().stateIn(viewModelScope)
    private val _homeScreenHeaderState: MutableStateFlow<IdeaEntity> =
        MutableStateFlow(initialIdeaEntity)
    val homeScreenHeaderState: StateFlow<IdeaEntity> = _homeScreenHeaderState.asStateFlow()

    fun onPriorityIconClick() {
        _homeScreenHeaderState.apply {
            value = value.copy(priority = incrementPriority(value.priority))
        }
    }

    private fun incrementPriority(priority: Int): Int = when (priority) {
        PRIORITY_GREEN -> PRIORITY_RED
        else           -> priority + 1
    }

    fun onIdeaTextChanged(ideaText: String) {
        _homeScreenHeaderState.value = _homeScreenHeaderState.value.copy(ideaText = ideaText)
    }

    fun onActionButtonClick() {
        viewModelScope.launch {
            val idea: IdeaEntity = _homeScreenHeaderState.value.copy(date = currentDate())
            if (idea.id == 0L) {
                insertIdea.execute(idea)
            } else {
                updateIdea.execute(idea)
            }
            _homeScreenHeaderState.value = initialIdeaEntity
        }
    }

    fun onContextMenuDeleteItemClick(ideaEntity: IdeaEntity) =
        viewModelScope.launch { deleteIdea.execute(ideaEntity) }

    fun onContextMenuEditItemClick(ideaEntity: IdeaEntity) {
        _homeScreenHeaderState.value = ideaEntity
    }

    private fun currentDate(): Long = Calendar.getInstance().timeInMillis
}