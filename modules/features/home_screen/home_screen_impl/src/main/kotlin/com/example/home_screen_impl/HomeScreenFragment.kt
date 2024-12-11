package com.example.home_screen_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.core_api.contracts.AppWithFacade
import com.example.core_api.contracts.UserAuthenticationContract
import com.example.home_screen_impl.databinding.FragmentHomeScreenBinding
import com.example.home_screen_impl.di.HomeScreenComponent
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.home_screen_impl.presentation.HomeScreenView
import com.example.home_screen_impl.presentation.HomeScreenViewFactory
import com.example.home_screen_impl.presentation.HomeScreenViewModel
import com.example.home_screen_impl.presentation.HomeScreenViewModelFactory
import com.example.infrastructure.base_blueprints.fragment.BaseFragment
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
            lifecycle,
            (requireActivity() as UserAuthenticationContract).getUserAuthenticatedStateFlow()
        ).inject(this)

    @Inject
    override lateinit var viewModelFactory: HomeScreenViewModelFactory

    @Inject
    override lateinit var router: HomeScreenRouter

    @Inject
    lateinit var viewFactory: HomeScreenViewFactory
    override lateinit var view: HomeScreenView
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAndSetViewModel(HomeScreenViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewBinding(FragmentHomeScreenBinding.inflate(inflater, container, false))
        view = viewFactory.create(binding, childFragmentManager)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().apply {
            onBackPressedCallback = onBackPressedDispatcher.addCallback(this) { finish() }
        }
    }

    override fun onResume() {
        super.onResume()
        onBackPressedCallback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        onBackPressedCallback.isEnabled = false
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editItem -> view.onContextMenuEditItemClick()
            R.id.deleteItem -> view.onContextMenuDeleteItemClick()
            else -> super.onContextItemSelected(item)
        }
    }

    companion object {
        fun newInstance() = HomeScreenFragment()
    }
}