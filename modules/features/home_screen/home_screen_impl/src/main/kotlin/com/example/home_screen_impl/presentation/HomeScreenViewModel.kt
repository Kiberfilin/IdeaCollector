package com.example.home_screen_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.CheckIsPasswordCorrectInputPort
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
    private val isPasswordEnabled: IsPasswordEnabledInputPort,
    private val checkIsPasswordCorrect: CheckIsPasswordCorrectInputPort,
    private val _isUserAuthenticated: MutableStateFlow<Boolean>
) : BaseViewModel<HomeScreenRouter>() {
    private val initialIdeaEntity: IdeaEntity
    private val _homeScreenState: MutableStateFlow<HomeScreenState>

    init {
        initialIdeaEntity =
            IdeaEntity(id = 0, ideaText = EMPTY_STRING, date = currentDate(), Priority.HIGH)
        _homeScreenState = MutableStateFlow(HomeScreenState(entity = initialIdeaEntity))
    }

    fun isUserAuthenticated(): StateFlow<Boolean> = _isUserAuthenticated.asStateFlow()

    fun onLongActionButtonClick(onPasswordSet: () -> Unit): Boolean {
        return if (!_isUserAuthenticated.value) {
            onPasswordSet.invoke()
            true
        } else {
            router?.navigateToSettingsScreen() != null
        }
    }

    suspend fun ideas(): StateFlow<List<IdeaEntity>> = getIdeas.execute().stateIn(viewModelScope)
    val homeScreenHeaderState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()
    fun onPriorityIconClick() = _homeScreenState.value.entity.let {
        _homeScreenState.value = _homeScreenState.value.copy(
            entity = it.copy(priority = incrementPriority(it.priority))
        )
    }

    private fun incrementPriority(priority: Priority): Priority = when (priority) {
        Priority.HIGH -> Priority.MEDIUM
        Priority.MEDIUM -> Priority.LOW
        Priority.LOW -> Priority.HIGH
        Priority.NONE -> throw IllegalArgumentException("Priority must not be ${Priority.NONE.name}")
    }

    fun onIdeaTextChanged(ideaText: String) = _homeScreenState.value.entity.let {
        _homeScreenState.value = _homeScreenState.value.copy(entity = it.copy(ideaText = ideaText))
    }

    fun onActionButtonClick() = _homeScreenState.value.entity.let {
        if (it.ideaText != EMPTY_STRING) {
            viewModelScope.launch {
                val idea: IdeaEntity = it.copy(date = currentDate())
                if (idea.id == 0L) {
                    insertIdea.execute(idea)
                } else {
                    updateIdea.execute(idea)
                }
                _homeScreenState.value = HomeScreenState(entity = initialIdeaEntity)
            }
        }
    }

    fun onContextMenuDeleteItemClick(ideaEntity: IdeaEntity) =
        viewModelScope.launch { deleteIdea.execute(ideaEntity) }

    fun onContextMenuEditItemClick(ideaEntity: IdeaEntity) {
        _homeScreenState.value = _homeScreenState.value.copy(entity = ideaEntity)
    }

    private fun currentDate(): Long = Calendar.getInstance().timeInMillis
    fun isPasswordEnabled(): Boolean = isPasswordEnabled.execute()

    suspend fun onPasswordChecking(enteredPassword: String): Boolean =
        checkIsPasswordCorrect.execute(enteredPassword)

    companion object {
        private const val EMPTY_STRING: String = ""
    }
}