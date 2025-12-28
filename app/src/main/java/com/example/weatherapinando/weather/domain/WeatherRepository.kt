package com.example.weatherapinando.weather.domain

import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails

interface WeatherRepository {

    suspend fun searchCities(query: String): WeatherResultRepository<List<Location>>

    suspend fun getWeatherByCity(cityName: String): WeatherResultRepository<WeatherDetails>

}
