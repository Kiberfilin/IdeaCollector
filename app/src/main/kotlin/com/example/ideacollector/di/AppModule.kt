package com.example.ideacollector.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.core_api.di.qualifier.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideDefaultSharedPreferences(@Application context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}