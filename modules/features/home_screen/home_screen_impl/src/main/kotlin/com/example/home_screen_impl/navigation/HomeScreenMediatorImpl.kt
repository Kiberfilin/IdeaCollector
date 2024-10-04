package com.example.home_screen_impl.navigation

import androidx.navigation.NavController
import com.example.home_screen_api.HomeScreenMediator
import com.example.home_screen_impl.R
import javax.inject.Inject

class HomeScreenMediatorImpl @Inject constructor(): HomeScreenMediator {
    override fun openHomeScreen(navController: NavController) {
        navController.navigate(R.id.homeScreenFragment)
    }
}