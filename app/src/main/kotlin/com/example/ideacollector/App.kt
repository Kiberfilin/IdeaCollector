package com.example.ideacollector

import android.app.Application
import com.example.core_api.contracts.AppWithFacade
import com.example.core_api.contracts.ProvidersFacade
import com.example.ideacollector.di.FacadeComponent
import com.example.ideacollector.infrastructure.ThemeManager
import kotlinx.coroutines.flow.Flow

class App : Application(), AppWithFacade {
    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(applicationContext)
            .also {
                facadeComponent = it
                val themeFLow: Flow<String?> = it.provideGetThemeFlowInputPort().execute()
                ThemeManager(this, themeFLow).subscribe()
            }
    }

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }
}