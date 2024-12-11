package com.example.ideacollector

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.contracts.AppWithFacade
import com.example.core_api.contracts.UserAuthenticationContract
import com.example.ideacollector.di.MainActivityComponent
import com.example.ideacollector.infrastructure.MainActivityViewModel
import com.example.ideacollector.infrastructure.MainActivityViewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainActivity : AppCompatActivity(), UserAuthenticationContract {
    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        MainActivityComponent.makeMainActivityComponent(
            (application as AppWithFacade).getFacade()
        ).inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun getUserAuthenticatedStateFlow(): MutableStateFlow<Boolean> =
        viewModel.getUserAuthStateFlow()
}