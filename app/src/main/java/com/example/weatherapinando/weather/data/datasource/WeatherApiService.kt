package com.example.weatherapinando.weather.data.datasource

import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails

interface WeatherApiService {

    suspend fun searchLocations(query: String): List<Location>

    suspend fun fetchWeatherDetails(cityName: String): WeatherDetails

}