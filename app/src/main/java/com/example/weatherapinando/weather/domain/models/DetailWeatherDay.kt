package com.example.weatherapinando.weather.domain.models

/**
 * Represents detailed weather metrics for a specific day.
 *
 * @property temperatureCelsius The average temperature recorded throughout the day in Celsius.
 * @property windKph The average wind speed throughout the day in kilometers per hour.
 * @property maxWindKph The maximum wind gust speed recorded during the day in kilometers per hour.
 * @property descriptionWeather The weather condition details and descriptions.
 */
data class DetailWeatherDay(
    val temperatureCelsius: Double,
    val windKph: Double,
    val maxWindKph: Double,
    val descriptionWeather: DescriptionWeather
)