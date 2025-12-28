package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Represents the serialized daily weather data retrieved from the data source.
 * This class serves as a data transfer object (DTO) for parsing weather information
 * specific to a single day.
 */
data class Day(
    @SerializedName("avgtemp_c") val temperatureCelsius: Double,
    @SerializedName("condition") val descriptionWeather: DescriptionWeather,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
)