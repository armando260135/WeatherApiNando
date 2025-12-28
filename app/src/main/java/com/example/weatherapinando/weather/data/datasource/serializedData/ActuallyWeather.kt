package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the current weather conditions returned by the API.
 *
 * @property temperatureCelsius The current temperature in degrees Celsius.
 * @property descriptionWeather An object containing the condition text and icon.
 * @property windKph The current wind speed measured in kilometers per hour.
 * @property lastUpdated The timestamp of when the weather data was last refreshed.
 */
data class ActuallyWeather(
    @SerializedName("temp_c") val temperatureCelsius: Double,
    @SerializedName("condition") val descriptionWeather: DescriptionWeather,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("last_updated") val lastUpdated: String
)