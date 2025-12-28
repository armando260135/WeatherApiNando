package com.example.weatherapinando.weather.commons_core

import com.example.weatherapinando.weather.domain.models.ActuallyWeather
import com.example.weatherapinando.weather.domain.models.DescriptionWeather
import com.example.weatherapinando.weather.domain.models.DetailWeatherDay
import com.example.weatherapinando.weather.domain.models.ListDaysWeather
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDayDetail
import com.example.weatherapinando.weather.domain.models.WeatherDetails

object CommonsCore {

    val EMPTY = WeatherDetails(
        location = Location(locationSearch = "", country = ""),
        actualWeather = ActuallyWeather(
            currentTemperatureC = 0.0,
            windInKph = 0.0,
            descriptionWeather = DescriptionWeather(
                descriptionWeather = "",
                iconWeather = ""
            ),
            lastUpdated = "--"
        ),
        listDaysWeather = ListDaysWeather(
            listDaysWeather = listOf(
                WeatherDayDetail(
                    date = "",
                    detailWeatherDay = DetailWeatherDay(
                        temperatureCelsius = 0.0,
                        descriptionWeather = DescriptionWeather(
                            descriptionWeather = "",
                            iconWeather = ""
                        ),
                        windKph = 0.0,
                        maxWindKph = 0.0
                    )
                )
            )
        )
    )
}