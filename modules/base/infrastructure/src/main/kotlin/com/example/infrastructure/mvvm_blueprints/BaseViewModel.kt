package com.example.infrastructure.mvvm_blueprints

import androidx.lifecycle.ViewModel
import com.example.infrastructure.base_blueprints.BaseRouter
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<R : BaseRouter>(private val themeFlow: Flow<String?>) : ViewModel() {
    protected var router: R? = null

    fun provideSavedTheme(): Flow<String?> = themeFlow
    fun bindRouter(routerForBinding: R) {
        router = routerForBinding
    }

    fun unbindRouter() {
        router = null
    }
}