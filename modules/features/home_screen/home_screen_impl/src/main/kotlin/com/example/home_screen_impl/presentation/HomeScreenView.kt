package com.example.home_screen_impl.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.infrastructure.mvvm_blueprints.BaseView
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
        viewBinding.textView.setOnClickListener {
            viewModel.handleOnTextClick()
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        lifecycle.coroutineScope.launch {
            viewModel.getAllIdeas().collect {
                Log.i("getAllIdeas()", it.toString())
            }
        }
        super.onStart(owner)
    }
}

@AssistedFactory
interface HomeScreenViewFactory {
    fun create(viewBinding: FragmentHomeScreenBinding): HomeScreenView
}