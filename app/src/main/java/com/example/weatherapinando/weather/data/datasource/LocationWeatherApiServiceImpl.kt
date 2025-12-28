package com.example.weatherapinando.weather.data.datasource

import com.example.weatherapinando.BuildConfig
import com.example.weatherapinando.weather.data.datasource.api.ApiServices
import com.example.weatherapinando.weather.data.datasource.mapper.toDomain
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationWeatherApiServiceImpl @Inject constructor(
    private val locationService: ApiServices,
) : WeatherApiService {

    private val apiKey = BuildConfig.WEATHER_API_KEY

    override suspend fun searchLocations(query: String): List<Location> {
        return locationService.weatherForInitialLocation(
            apiKey = apiKey,
            query = query
        ) ?: emptyList()
    }

    override suspend fun fetchWeatherDetails(cityName: String): WeatherDetails {
        val response = locationService.getWeather(
            apiKey = apiKey,
            location = cityName,
            days = 3
        )
        return response.toDomain()
    }
}
