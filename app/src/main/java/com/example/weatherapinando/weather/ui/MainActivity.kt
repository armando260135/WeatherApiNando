package com.example.weatherapinando.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherapinando.ui.theme.WeatherApiNandoTheme
import com.example.weatherapinando.weather.ui.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApiNandoTheme {
                NavigationWrapper()
            }
        }
    }
}