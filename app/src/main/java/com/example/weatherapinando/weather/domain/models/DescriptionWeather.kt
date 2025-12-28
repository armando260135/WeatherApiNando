package com.example.weatherapinando.weather.domain.models

/**
 * Represents the descriptive information of a weather condition.
 *
 * This domain model is used to encapsulate textual details and metadata
 * regarding specific weather states as provided by the weather service.
 */
data class DescriptionWeather(
    val descriptionWeather: String,
    val iconWeather: String
)