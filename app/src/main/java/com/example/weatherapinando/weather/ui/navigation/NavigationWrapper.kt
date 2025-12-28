package com.example.weatherapinando.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapinando.weather.ui.HomeScreen
import com.example.weatherapinando.weather.ui.SearchScreen
import com.example.weatherapinando.weather.ui.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(navigateToHomeScreen = {
                navController.popBackStack()
                navController.navigate(Home)
            })
        }

        composable<Home> {
            HomeScreen()
        }

        composable<Search> {
            SearchScreen()
        }
    }
}