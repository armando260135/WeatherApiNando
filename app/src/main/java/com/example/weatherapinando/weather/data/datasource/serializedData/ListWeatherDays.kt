package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the forecast weather data container.
 *
 * @property listWeatherDays A list of [WeatherDay] objects containing weather information for specific days.
 */
data class ListWeatherDays(
    @SerializedName("forecastday") val listWeatherDays: List<WeatherDay>
)