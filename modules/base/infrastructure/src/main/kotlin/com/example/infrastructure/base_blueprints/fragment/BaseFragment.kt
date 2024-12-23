package com.example.infrastructure.base_blueprints.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.infrastructure.base_blueprints.BaseRouter
import com.example.infrastructure.mvvm_blueprints.fragment.BaseFragmentView
import com.example.infrastructure.mvvm_blueprints.BaseViewModel

abstract class BaseFragment
<VB : ViewBinding,
        R : BaseRouter,
        VM : ViewModel,
        VMF : ViewModelProvider.Factory,
        V : BaseFragmentView<VB, VM>> : Fragment() {
    protected abstract fun daggerInit()
    abstract var viewModelFactory: VMF
    abstract var router: R
    private lateinit var viewModel: VM
    abstract var view: V
    private var _binding: VB? = null

    protected val binding
        get() = _binding
            ?: throw NullPointerException("The private var _binding: VB? equals null in ${this::class.simpleName}")

    protected fun initViewBinding(viewBinding: VB) {
        _binding = viewBinding
    }

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
        _binding = null
    }
}