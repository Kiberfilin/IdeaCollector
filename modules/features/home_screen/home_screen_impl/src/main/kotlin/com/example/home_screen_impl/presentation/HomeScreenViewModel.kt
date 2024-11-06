package com.example.home_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.domain.entities.Priority
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.home_screen_impl.presentation.state.HomeScreenState
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
    private val isPasswordEnabledInputPort: IsPasswordEnabledInputPort,
) : BaseViewModel<HomeScreenRouter>() {
    private val initialIdeaEntity: IdeaEntity
    private var _homeScreenState: MutableStateFlow<HomeScreenState>

    init {
        initialIdeaEntity =
            IdeaEntity(id = 0, ideaText = EMPTY_STRING, date = currentDate(), Priority.HIGH)
        _homeScreenState = if (isPasswordEnabledInputPort.execute()) {
            MutableStateFlow(HomeScreenState.Locked)
        } else {
            MutableStateFlow(HomeScreenState.Unlocked(initialIdeaEntity))
        }
    }

    fun onLongActionButtonClick(): Boolean = router?.navigateToSettingsScreen() != null
    suspend fun ideas(): StateFlow<List<IdeaEntity>> = getIdeas.execute().stateIn(viewModelScope)
    val homeScreenHeaderState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()
    fun onPriorityIconClick() {
        when (_homeScreenState.value) {
            is HomeScreenState.Unlocked -> {
                val tmpEntity: IdeaEntity =
                    (_homeScreenState.value as HomeScreenState.Unlocked).entity
                _homeScreenState.value =
                    (_homeScreenState.value as HomeScreenState.Unlocked).copy(
                        entity = tmpEntity.copy(priority = incrementPriority(tmpEntity.priority))
                    )
            }

            HomeScreenState.Locked      -> Unit
        }
    }

    private fun incrementPriority(priority: Priority): Priority = when (priority) {
        Priority.HIGH   -> Priority.MEDIUM
        Priority.MEDIUM -> Priority.LOW
        Priority.LOW    -> Priority.HIGH
        Priority.NONE   -> throw IllegalArgumentException("Priority must not be ${Priority.NONE.name}")
    }

    fun onIdeaTextChanged(ideaText: String) =
        if (_homeScreenState.value is HomeScreenState.Unlocked) {
            val tmpEntity: IdeaEntity = (_homeScreenState.value as HomeScreenState.Unlocked).entity
            _homeScreenState.value = (_homeScreenState.value as HomeScreenState.Unlocked).copy(
                entity = tmpEntity.copy(ideaText = ideaText)
            )
        } else {
            Unit
        }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun onActionButtonClick() = if (_homeScreenState.value is HomeScreenState.Unlocked) {
        val tmpEntity = (_homeScreenState.value as HomeScreenState.Unlocked).entity
        if (tmpEntity.ideaText != EMPTY_STRING) {
            viewModelScope.launch {
                val idea: IdeaEntity = tmpEntity.copy(date = currentDate())
                if (idea.id == 0L) {
                    insertIdea.execute(idea)
                } else {
                    updateIdea.execute(idea)
                }
                _homeScreenState.value = HomeScreenState.Unlocked(initialIdeaEntity)
            }
        } else {
            Unit
        }
    } else {
        Unit
    }

    fun onContextMenuDeleteItemClick(ideaEntity: IdeaEntity) =
        viewModelScope.launch { deleteIdea.execute(ideaEntity) }

    fun onContextMenuEditItemClick(ideaEntity: IdeaEntity) {
        _homeScreenState.value =
            (_homeScreenState.value as HomeScreenState.Unlocked).copy(
                entity = ideaEntity
            )
    }

    private fun currentDate(): Long = Calendar.getInstance().timeInMillis
    fun onConfigureScreen() {
        _homeScreenState.value = if (isPasswordEnabledInputPort.execute()) {
            HomeScreenState.Locked
        } else {
            HomeScreenState.Unlocked(initialIdeaEntity)
        }
    }

    companion object {
        private const val EMPTY_STRING: String = ""
    }
}