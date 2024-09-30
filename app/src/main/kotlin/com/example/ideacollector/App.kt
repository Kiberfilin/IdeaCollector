package com.example.ideacollector

import android.app.Application
import com.example.core_api.contracts.AppWithFacade
import com.example.core_api.contracts.ProvidersFacade
import com.example.ideacollector.di.FacadeComponent

class App : Application(), AppWithFacade {
    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(applicationContext)
            .also { facadeComponent = it }
    }
}