package com.example.infrastructure.base_blueprints

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import com.example.core_api.constants.THEME_AUTO
import com.example.core_api.constants.THEME_DARK
import com.example.core_api.constants.THEME_LIGHT
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import kotlinx.coroutines.launch

interface ThemeContractActor {
    @RequiresApi(Build.VERSION_CODES.S)
    fun prepareScreenThemeAfterApiR(context: Context, lifecycle: Lifecycle, viewModel: ViewModel) {
        val uiModeManager: UiModeManager =
            context.getSystemService(UI_MODE_SERVICE) as UiModeManager
        lifecycle.coroutineScope.launch {
            (viewModel as BaseViewModel<*>).provideSavedTheme().flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { theme: String? ->
                theme?.let {
                    when (it) {
                        THEME_LIGHT -> uiModeManager.setApplicationNightMode(
                            UiModeManager.MODE_NIGHT_NO
                        )

                        THEME_DARK  -> uiModeManager.setApplicationNightMode(
                            UiModeManager.MODE_NIGHT_YES
                        )

                        THEME_AUTO  -> uiModeManager.setApplicationNightMode(
                            UiModeManager.MODE_NIGHT_AUTO
                        )

                        else        -> throw exception(it)
                    }
                }
            }
        }
    }

    fun prepareScreenTheme(lifecycle: Lifecycle, viewModel: ViewModel) {
        lifecycle.coroutineScope.launch {
            (viewModel as BaseViewModel<*>).provideSavedTheme().flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { theme: String? ->
                theme?.let {
                    when (it) {
                        THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO
                        )

                        THEME_DARK  -> AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO
                        )

                        THEME_AUTO  -> AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        )

                        else        -> throw exception(it)
                    }
                }
            }
        }
    }

    private fun exception(theme: String): IllegalStateException = IllegalStateException(
        "Night mode may be $THEME_AUTO, $THEME_LIGHT or $THEME_DARK, but not $theme!"
    )
}