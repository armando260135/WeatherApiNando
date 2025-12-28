package com.example.weatherapinando.weather.domain

import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import javax.inject.Inject

/**
 * Use case responsible for retrieving detailed weather information for a specific location.
 *
 * This class interacts with the [WeatherRepository] to fetch data such as current conditions
 * and forecasts based on the provided city name.
 *
 * @property repository The repository used to fetch location-specific weather details.
 */
class GetResultLocations @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(countryName: String): WeatherResultRepository<List<Location>> {
        return repository.searchCities(countryName)
    }
}

