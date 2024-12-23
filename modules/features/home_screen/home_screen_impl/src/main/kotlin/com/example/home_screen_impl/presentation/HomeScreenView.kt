package com.example.home_screen_impl.presentation

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core_api.clean.domain.entities.Priority
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.home_screen_impl.presentation.recyclerview.IdeasRecyclerViewAdapter
import com.example.home_screen_impl.presentation.state.HomeScreenState
import com.example.infrastructure.mvvm_blueprints.fragment.BaseFragmentView
import com.example.ui_kit.dialogs.PasswordInputDialog
import com.example.ui_kit.helpers.clearFocusAndHideKeyboard
import com.example.ui_kit.helpers.requestFocusAndShowKeyboard
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HomeScreenView @AssistedInject constructor(
    @Assisted viewBinding: FragmentHomeScreenBinding,
    @Assisted private val fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : BaseFragmentView<FragmentHomeScreenBinding, HomeScreenViewModel>(viewBinding, lifecycle) {
    private val ideasRecyclerViewAdapter = IdeasRecyclerViewAdapter()
    private fun showPasswordInputDialog() = PasswordInputDialog
        .newInstance(viewModel::onPasswordChecking)
        .show(fragmentManager, null)

    private fun configureScreen() {
        viewBinding.ideaPriorityIcon.apply {
            setBlueCornerVisible(true)
            setOnClickListener { viewModel.onPriorityIconClick() }
        }
        viewBinding.homeScreenHeaderActionButton.apply {
            setOnClickListener {
                viewModel.onActionButtonClick()
                viewBinding.ideaEditText.clearFocusAndHideKeyboard()
            }
            setOnLongClickListener {
                viewModel.onLongActionButtonClick(::showPasswordInputDialog)
            }
        }
        viewBinding.ideaEditText.addTextChangedListener {
            viewModel.onIdeaTextChanged(it.toString())
        }
        viewBinding.ideasRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setLayoutManager(layoutManager)
            adapter = ideasRecyclerViewAdapter
            val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
        }
        viewBinding.lockIcon.setOnClickListener {
            showPasswordInputDialog()
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        configureScreen()
        lifecycle.coroutineScope.launch {
            viewModel.ideas()
                .flowWithLifecycle(
                    lifecycle = lifecycle,
                    minActiveState = Lifecycle.State.STARTED
                )
                .collect {
                    ideasRecyclerViewAdapter.submitList(it)
                }
        }
        lifecycle.coroutineScope.launch {
            viewModel.homeScreenHeaderState.flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect {
                renderHeaderState(it)
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.isUserAuthenticated().flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { isAuthenticated: Boolean ->
                lockScreen(!isAuthenticated && viewModel.isPasswordEnabled())
            }
        }
    }

    private fun renderHeaderState(state: HomeScreenState) {
        setPriority(state.entity.priority)
        setIdeaText(state.entity.ideaText)
    }

    private fun lockScreen(lock: Boolean) {
        viewBinding.apply {
            if (lock) {
                ideasRecyclerView.visibility = View.GONE
                lockScreen.visibility = View.VISIBLE
            } else {
                ideasRecyclerView.visibility = View.VISIBLE
                lockScreen.visibility = View.GONE
            }
        }
    }

    private fun setIdeaText(ideaText: String) {
        if (ideaText != viewBinding.ideaEditText.text.toString()) {
            viewBinding.ideaEditText.setText(ideaText)
        }
    }

    private fun setPriority(priority: Priority) {
        when (priority) {
            Priority.LOW, Priority.MEDIUM, Priority.HIGH -> {
                viewBinding.ideaPriorityIcon.setPriority(priority.code)
            }

            Priority.NONE -> {
                throw IllegalArgumentException("Priority must not be ${Priority.NONE.name}")
            }
        }
    }

    fun onContextMenuEditItemClick(): Boolean {
        viewModel.onContextMenuEditItemClick(
            ideasRecyclerViewAdapter.getCurrentIdeaFromContextMenu()
        )
        viewBinding.ideaEditText.requestFocusAndShowKeyboard()
        return true
    }

    fun onContextMenuDeleteItemClick(): Boolean {
        viewModel.onContextMenuDeleteItemClick(
            ideasRecyclerViewAdapter.getCurrentIdeaFromContextMenu()
        )
        return true
    }
}

@AssistedFactory
interface HomeScreenViewFactory {
    fun create(
        viewBinding: FragmentHomeScreenBinding, childFragmentManager: FragmentManager
    ): HomeScreenView
}