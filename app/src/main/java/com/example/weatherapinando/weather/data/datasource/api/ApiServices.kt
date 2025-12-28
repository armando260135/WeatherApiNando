package com.example.weatherapinando.weather.data.datasource.api

import com.example.weatherapinando.weather.data.datasource.serializedData.Weather
import com.example.weatherapinando.weather.domain.models.Location
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Qualifier

interface ApiServices {
    @GET("search.json")
    suspend fun weatherForInitialLocation(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): List<Location>

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): Weather
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiUrl()