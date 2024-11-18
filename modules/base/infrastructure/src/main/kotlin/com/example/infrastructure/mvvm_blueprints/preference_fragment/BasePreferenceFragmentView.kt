package com.example.infrastructure.mvvm_blueprints.preference_fragment

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.infrastructure.base_blueprints.ThemeContractActor

abstract class BasePreferenceFragmentView<VM : ViewModel>(
    protected val lifecycle: Lifecycle
) : DefaultLifecycleObserver, ThemeContractActor {

    init {
        registerObserver()
    }

    protected lateinit var viewModel: VM

    fun setProperViewModel(viewModel: VM) {
        this.viewModel = viewModel
    }

    protected fun registerObserver() {
        lifecycle.addObserver(this)
    }

    protected fun removeObserver() {
        lifecycle.removeObserver(this)
    }

    override fun onStop(owner: LifecycleOwner) {
        removeObserver()
        super.onDestroy(owner)
    }
}