package com.example.weatherapinando.weather.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Represents the splash screen destination in the navigation graph.
 */
@Serializable
object Splash

/**
 * Represents the home screen destination in the navigation graph,
 * typically used to display the main weather details.
 */
@Serializable
object Home

/**
 * Represents the navigation destination for the search screen,
 * allowing users to look up weather information for different locations.
 */
@Serializable
object Search