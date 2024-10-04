package com.example.home_screen_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core_api.contracts.AppWithFacade
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.home_screen_impl.di.HomeScreenComponent
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.home_screen_impl.presentation.HomeScreenView
import com.example.home_screen_impl.presentation.HomeScreenViewFactory
import com.example.home_screen_impl.presentation.HomeScreenViewModel
import com.example.home_screen_impl.presentation.HomeScreenViewModelFactory
import com.example.infrastructure.base_blueprints.BaseFragment
import javax.inject.Inject

class HomeScreenFragment : BaseFragment<
        FragmentHomeScreenBinding,
        HomeScreenRouter,
        HomeScreenViewModel,
        HomeScreenViewModelFactory,
        HomeScreenView>() {
    override fun daggerInit() =
        HomeScreenComponent.makeHomeScreenComponent(
            (requireActivity().application as AppWithFacade).getFacade(),
            findNavController(),
            lifecycle
        ).inject(this)

    @Inject
    override lateinit var viewModelFactory: HomeScreenViewModelFactory

    @Inject
    override lateinit var router: HomeScreenRouter

    @Inject
    lateinit var viewFactory: HomeScreenViewFactory
    override lateinit var view: HomeScreenView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAndSetViewModel(HomeScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewBinding(FragmentHomeScreenBinding.inflate(inflater, container, false))
        view = viewFactory.create(binding)
        return binding.root
    }

    companion object {
        fun newInstance() = HomeScreenFragment()
    }
}