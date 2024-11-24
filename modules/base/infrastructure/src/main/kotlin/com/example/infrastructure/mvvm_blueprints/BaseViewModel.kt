package com.example.infrastructure.mvvm_blueprints

import androidx.lifecycle.ViewModel
import com.example.infrastructure.base_blueprints.BaseRouter

abstract class BaseViewModel<R : BaseRouter> : ViewModel() {
    protected var router: R? = null

    fun bindRouter(routerForBinding: R) {
        router = routerForBinding
    }

    fun unbindRouter() {
        router = null
    }
}