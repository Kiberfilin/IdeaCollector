package com.example.infrastructure.base_blueprints.preference_fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.example.infrastructure.base_blueprints.BaseRouter
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import com.example.infrastructure.mvvm_blueprints.preference_fragment.BasePreferenceFragmentView

abstract class BasePreferenceFragment
<R : BaseRouter,
        VM : ViewModel,
        VMF : ViewModelProvider.Factory,
        V : BasePreferenceFragmentView<VM>> : PreferenceFragmentCompat() {
    abstract fun daggerInit()
    abstract var viewModelFactory: VMF
    abstract var router: R
    private lateinit var viewModel: VM
    abstract var view: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInit()
    }

    protected fun createAndSetViewModel(javaClass: Class<VM>) {
        viewModel = ViewModelProvider(this, viewModelFactory)[javaClass]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        @Suppress("UNCHECKED_CAST")
        (viewModel as BaseViewModel<R>).bindRouter(router)
        this.view.setProperViewModel(viewModel)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        @Suppress("UNCHECKED_CAST")
        (viewModel as BaseViewModel<R>).unbindRouter()
    }
}