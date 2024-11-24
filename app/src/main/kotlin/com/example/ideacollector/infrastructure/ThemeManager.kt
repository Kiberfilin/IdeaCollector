package com.example.ideacollector.infrastructure

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.example.core_api.constants.THEME_AUTO
import com.example.core_api.constants.THEME_DARK
import com.example.core_api.constants.THEME_LIGHT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal class ThemeManager(private val context: Context, private val themeFlow: Flow<String?>) {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    fun subscribe() {
        scope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                prepareScreenThemeAfterApiR()
            } else {
                prepareScreenTheme()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private suspend fun prepareScreenThemeAfterApiR() {
        val uiModeManager: UiModeManager =
            context.getSystemService(UI_MODE_SERVICE) as UiModeManager
        themeFlow.collect { theme: String? ->
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

    private suspend fun prepareScreenTheme() {
        themeFlow.collect { theme: String? ->
            theme?.let {
                when (it) {
                    THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )

                    THEME_DARK  -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )

                    THEME_AUTO  -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )

                    else        -> throw exception(it)
                }
            }
        }
    }

    private fun exception(theme: String): IllegalStateException = IllegalStateException(
        "Night mode may be $THEME_AUTO, $THEME_LIGHT or $THEME_DARK, but not $theme!"
    )
}