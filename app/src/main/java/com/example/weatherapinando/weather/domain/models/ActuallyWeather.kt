package com.example.weatherapinando.weather.domain.models

/**
 * Data class representing the current weather conditions.
 *
 * @property currentTemperatureC The current temperature in Celsius.
 * @property descriptionWeather The weather condition description.
 * @property windInKph The wind speed in kilometers per hour.
 */
data class ActuallyWeather(
    val currentTemperatureC: Double,
    val descriptionWeather: DescriptionWeather,
    val windInKph: Double,
    val lastUpdated: String
)