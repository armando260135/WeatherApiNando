package com.example.weatherapinando.weather.data.datasource.api

import com.example.weatherapinando.weather.data.datasource.LocationWeatherApiServiceImpl
import com.example.weatherapinando.weather.data.datasource.WeatherApiService
import com.example.weatherapinando.weather.domain.DefaultWeatherRepository
import com.example.weatherapinando.weather.domain.WeatherRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindWeatherApiService(
        locationWeatherApiServiceImpl: LocationWeatherApiServiceImpl
    ): WeatherApiService

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        defaultWeatherRepository: DefaultWeatherRepository
    ): WeatherRepository
}