package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Data representation of the weather conditions for a specific day.
 *
 * This class is typically used for mapping serialized data received from a
 * data source (such as a remote API or local database) into the application's
 * data layer.
 */
data class WeatherDay(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
)