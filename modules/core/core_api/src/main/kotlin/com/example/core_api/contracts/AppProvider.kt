package com.example.core_api.contracts

import android.content.Context
import android.content.SharedPreferences
import com.example.core_api.di.qualifier.Application

interface AppProvider {
    @Application
    fun provideContext(): Context
    fun provideSharedPreferences(): SharedPreferences
}