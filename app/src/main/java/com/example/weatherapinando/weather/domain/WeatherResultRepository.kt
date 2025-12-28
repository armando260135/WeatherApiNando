package com.example.weatherapinando.weather.domain


/**
 * A sealed class that represents the result of a weather-related operation.
 *
 * This wrapper is used to handle successful data retrieval or failure scenarios
 * when interacting with weather data sources.
 *
 * @param T The type of data contained in the success result.
 */
sealed class WeatherResultRepository<out T> {
    data class Success<out T>(val data: T) : WeatherResultRepository<T>()
    data class Error(val message: String, val exception: Exception) : WeatherResultRepository<Nothing>()
}
