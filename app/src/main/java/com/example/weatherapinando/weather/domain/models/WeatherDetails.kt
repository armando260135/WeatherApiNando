package com.example.weatherapinando.weather.domain.models

/**
 * Model representing weather details for a specific location.
 *
 * @property location Location information.
 * @property actualWeather Current weather status.
 * @property listDaysWeather Weather forecast for several days.
 */
data class WeatherDetails(
    val location: Location,
    val actualWeather: ActuallyWeather,
    val listDaysWeather: ListDaysWeather
)