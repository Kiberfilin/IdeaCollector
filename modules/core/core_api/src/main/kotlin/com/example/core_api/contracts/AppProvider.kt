package com.example.core_api.contracts

import android.content.Context
import com.example.core_api.di.qualifier.Application

interface AppProvider {
    @Application
    fun provideContext(): Context
}