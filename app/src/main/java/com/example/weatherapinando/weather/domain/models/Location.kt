package com.example.weatherapinando.weather.domain.models

/**
 * Represents a geographic location.
 *
 * @property locationSearch The name or query used to search for the location.
 * @property country The country where the location is situated.
 */
data class Location(
    val locationSearch: String,
    val country: String,
)