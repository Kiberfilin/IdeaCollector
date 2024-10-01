package com.example.home_screen_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core_api.contracts.AppWithFacade
import com.example.home_screen_impl.di.HomeScreenComponent
import com.example.settings_api.SettingsScreenMediator
import javax.inject.Inject

class HomeScreenFragment : Fragment() {
    @Inject
    lateinit var settingsScreenMediator: SettingsScreenMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeScreenComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.textView).setOnClickListener {
            settingsScreenMediator.openSettingsScreen(findNavController())
        }
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newInstance() = HomeScreenFragment()
    }
}