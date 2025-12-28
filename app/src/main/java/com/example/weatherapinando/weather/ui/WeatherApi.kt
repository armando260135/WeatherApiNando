package com.example.weatherapinando.weather.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for the Weather app.
 *
 * This class is annotated with [HiltAndroidApp] to trigger Hilt's code generation,
 * serving as the dependency injection container at the application level.
 */
@HiltAndroidApp
class WeatherApi: Application()