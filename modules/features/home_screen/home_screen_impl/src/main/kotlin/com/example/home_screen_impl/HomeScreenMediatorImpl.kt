package com.example.home_screen_impl

import androidx.navigation.NavController
import com.example.home_screen_api.HomeScreenMediator
import javax.inject.Inject

class HomeScreenMediatorImpl @Inject constructor(): HomeScreenMediator {
    override fun openHomeScreen(navController: NavController) {
        navController.navigate(R.id.homeScreenFragment)
    }
}