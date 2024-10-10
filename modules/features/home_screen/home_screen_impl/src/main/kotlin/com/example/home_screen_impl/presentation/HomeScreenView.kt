package com.example.home_screen_impl.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.infrastructure.mvvm_blueprints.BaseView
import com.example.ui_kit.PriorityIcon
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HomeScreenView @AssistedInject constructor(
    @Assisted viewBinding: FragmentHomeScreenBinding,
    lifecycle: Lifecycle
) : BaseView<FragmentHomeScreenBinding, HomeScreenViewModel>(viewBinding, lifecycle) {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        /*viewBinding.textView.setOnClickListener {
            viewModel.handleOnTextClick()
        }*/
        viewBinding.ideaPriorityIcon.apply {
            setPriority(PriorityIcon.PRIORITY_YELLOW)
            setBlueCornerVisible(true)
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        lifecycle.coroutineScope.launch {
            viewModel.ideas()
                .flowWithLifecycle(lifecycle = lifecycle, minActiveState = Lifecycle.State.STARTED)
                .collect {
                    Log.i("viewModel.ideas()", it.toString())
                }
        }
    }
}

@AssistedFactory
interface HomeScreenViewFactory {
    fun create(viewBinding: FragmentHomeScreenBinding): HomeScreenView
}