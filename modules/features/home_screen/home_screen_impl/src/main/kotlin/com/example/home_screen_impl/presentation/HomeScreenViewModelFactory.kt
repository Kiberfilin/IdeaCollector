package com.example.home_screen_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.clean.domain.boundaries.use_cases.CheckIsPasswordCorrectInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_api.di.qualifier.Authentication
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class HomeScreenViewModelFactory @Inject constructor(
    private val getAllIdeas: GetAllIdeasInputPort,
    private val insertIdea: InsertIdeaInputPort,
    private val deleteIdea: DeleteIdeaInputPort,
    private val updateIdea: UpdateIdeaInputPort,
    private val isPasswordEnabledInputPort: IsPasswordEnabledInputPort,
    private val checkIsPasswordCorrect: CheckIsPasswordCorrectInputPort,
    @Authentication
    private val isUserAuthenticatedStateFlow: MutableStateFlow<Boolean>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) ->
                return HomeScreenViewModel(
                    getAllIdeas,
                    insertIdea,
                    deleteIdea,
                    updateIdea,
                    isPasswordEnabledInputPort,
                    checkIsPasswordCorrect,
                    isUserAuthenticatedStateFlow
                ) as T

            else                                                         -> throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}