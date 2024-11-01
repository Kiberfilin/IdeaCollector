package com.example.home_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.domain.entities.Priority
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
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
    private val updateIdea: UpdateIdeaInputPort,
) : BaseViewModel<HomeScreenRouter>() {

    private val initialIdeaEntity =
        IdeaEntity(id = 0, ideaText = EMPTY_STRING, date = currentDate(), Priority.HIGH)

    fun onLongActionButtonClick(): Boolean = router?.navigateToSettingsScreen() != null
    suspend fun ideas(): StateFlow<List<IdeaEntity>> = getIdeas.execute().stateIn(viewModelScope)
    private val _homeScreenHeaderState: MutableStateFlow<IdeaEntity> =
        MutableStateFlow(initialIdeaEntity)
    val homeScreenHeaderState: StateFlow<IdeaEntity> = _homeScreenHeaderState.asStateFlow()

    fun onPriorityIconClick() {
        _homeScreenHeaderState.apply {
            value = value.copy(priority = incrementPriority(value.priority))
        }
    }

    private fun incrementPriority(priority: Priority): Priority = when (priority) {
        Priority.HIGH   -> Priority.MEDIUM
        Priority.MEDIUM -> Priority.LOW
        Priority.LOW    -> Priority.HIGH
        Priority.NONE   -> throw IllegalArgumentException("Priority must not be ${Priority.NONE.name}")
    }

    fun onIdeaTextChanged(ideaText: String) {
        _homeScreenHeaderState.value = _homeScreenHeaderState.value.copy(ideaText = ideaText)
    }

    fun onActionButtonClick() {
        val tmpEntity = _homeScreenHeaderState.value
        if (tmpEntity.ideaText != EMPTY_STRING) {
            viewModelScope.launch {
                val idea: IdeaEntity = tmpEntity.copy(date = currentDate())
                if (idea.id == 0L) {
                    insertIdea.execute(idea)
                } else {
                    updateIdea.execute(idea)
                }
                _homeScreenHeaderState.value = initialIdeaEntity
            }
        }
    }

    fun onContextMenuDeleteItemClick(ideaEntity: IdeaEntity) =
        viewModelScope.launch { deleteIdea.execute(ideaEntity) }

    fun onContextMenuEditItemClick(ideaEntity: IdeaEntity) {
        _homeScreenHeaderState.value = ideaEntity
    }

    private fun currentDate(): Long = Calendar.getInstance().timeInMillis

    companion object {
        const val EMPTY_STRING: String = ""
    }
}