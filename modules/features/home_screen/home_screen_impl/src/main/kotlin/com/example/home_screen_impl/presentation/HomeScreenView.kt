package com.example.home_screen_impl.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.infrastructure.mvvm_blueprints.BaseView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

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
}

@AssistedFactory
interface HomeScreenViewFactory {
    fun create(viewBinding: FragmentHomeScreenBinding): HomeScreenView
}