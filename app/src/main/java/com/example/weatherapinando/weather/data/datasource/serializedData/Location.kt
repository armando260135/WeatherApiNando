package com.example.weatherapinando.weather.data.datasource.serializedData

import com.google.gson.annotations.SerializedName

/**
 * Data representation of the geographical location details returned by the weather service.
 *
 * This class typically encapsulates information such as the city name, region, country,
 * and coordinates (latitude and longitude) used to identify the source of the weather data.
 */
data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)