package com.example.weatherapinando.weather.domain.models
/**
 * Data class representing the detailed weather information for a specific day.
 *
 * @property date The date for which the weather details are provided, formatted as a String.
 * @property detailWeatherDay An instance of DetailWeatherDay containing detailed weather data for the day.
 */
data class WeatherDayDetail(
    val date: String,
    val detailWeatherDay: DetailWeatherDay,
)