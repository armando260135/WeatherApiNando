package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the visual and textual description of weather conditions.
 *
 * @property text A human-readable description of the weather condition (e.g., "Sunny", "Partly cloudy").
 * @property icon The URL or code representing the icon associated with the current weather state.
 */
data class DescriptionWeather(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String
)