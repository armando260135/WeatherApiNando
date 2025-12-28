package com.example.weatherapinando.weather.domain

import com.example.weatherapinando.weather.data.datasource.WeatherApiService
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val apiService: WeatherApiService,
) : WeatherRepository {

    override suspend fun searchCities(query: String): WeatherResultRepository<List<Location>> {
        return try {
            val response = apiService.searchLocations(query)
            WeatherResultRepository.Success(response)
        } catch (e: Exception) {
            mapToWeatherError(e)
        }
    }

    override suspend fun getWeatherByCity(cityName: String): WeatherResultRepository<WeatherDetails> {
        return try {
            val response = apiService.fetchWeatherDetails(cityName)
            WeatherResultRepository.Success(response)
        } catch (e: Exception) {
            mapToWeatherError(e)
        }
    }

    private fun mapToWeatherError(e: Exception): WeatherResultRepository.Error {
        return when (e) {
            is java.io.IOException -> {
                WeatherResultRepository.Error("No hay conexión a internet. Revisa tu red.", e)
            }

            is retrofit2.HttpException -> {
                val message = when (e.code()) {
                    401 -> "Llave de API inválida."
                    404 -> "Ciudad no encontrada."
                    else -> "Error en el servidor de clima."
                }
                WeatherResultRepository.Error(message, e)
            }

            else -> {
                WeatherResultRepository.Error("Ocurrió un error inesperado.", e)
            }
        }
    }
}





