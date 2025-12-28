package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Represents the serialized weather data received from the data source.
 *
 * This class serves as a Data Transfer Object (DTO) that maps the raw weather
 * information from the API response into a structured format for the application.
 */
data class Weather(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val actuallyWeather: ActuallyWeather,
    @SerializedName("forecast") val forecast: ListWeatherDays
)