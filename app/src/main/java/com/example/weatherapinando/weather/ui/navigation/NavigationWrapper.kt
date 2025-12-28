package com.example.weatherapinando.weather.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapinando.weather.ui.screen.search.SearchScreen
import com.example.weatherapinando.weather.ui.screen.splash.SplashScreen
import com.example.weatherapinando.weather.ui.screen.home.HomeScreen
import com.example.weatherapinando.weather.ui.viewmodels.HomeScreenViewModel
import com.example.weatherapinando.weather.ui.viewmodels.SearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(navigateToHomeScreen = {
                navController.popBackStack()
                navController.navigate(Home)
            })
        }

        composable<Home> {
            val viewModelHome = hiltViewModel<HomeScreenViewModel>()
            val viewModelSearch = hiltViewModel<SearchViewModel>()

            HomeScreen(
                viewModel = viewModelHome,
                viewModelSearch = viewModelSearch,
            )
        }
    }
}