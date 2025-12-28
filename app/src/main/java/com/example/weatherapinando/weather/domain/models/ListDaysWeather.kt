package com.example.weatherapinando.weather.domain.models

/**
 * Represents a collection or list of weather data spanning multiple days.
 * This domain model is used to encapsulate a series of weather information
 * typically used for displaying a multi-day forecast.
 */
data class ListDaysWeather(
    val listDaysWeather: List<WeatherDayDetail>
)