package com.example.weatherapinando.weather.data.datasource.mapper

import com.example.weatherapinando.weather.data.datasource.serializedData.Weather
import com.example.weatherapinando.weather.data.datasource.serializedData.WeatherDay
import com.example.weatherapinando.weather.domain.models.ActuallyWeather
import com.example.weatherapinando.weather.domain.models.DescriptionWeather
import com.example.weatherapinando.weather.domain.models.DetailWeatherDay
import com.example.weatherapinando.weather.domain.models.ListDaysWeather
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDayDetail
import com.example.weatherapinando.weather.domain.models.WeatherDetails

fun Weather.toDomain(): WeatherDetails {
    return WeatherDetails(
        location = Location(
            locationSearch = this.location.name,
            country = this.location.country
        ),
        actualWeather = ActuallyWeather(
            currentTemperatureC = this.actuallyWeather.temperatureCelsius,
            windInKph = this.actuallyWeather.windKph,
            descriptionWeather = DescriptionWeather(
                descriptionWeather = this.actuallyWeather.descriptionWeather.text,
                iconWeather = this.actuallyWeather.descriptionWeather.icon,
            ),
            lastUpdated = this.actuallyWeather.lastUpdated
        ),
        listDaysWeather = ListDaysWeather(
            listDaysWeather = this.forecast.listWeatherDays.map { it.toDomain() }
        )
    )
}

fun WeatherDay.toDomain(): WeatherDayDetail {
    return WeatherDayDetail(
        date = this.date,
        detailWeatherDay = DetailWeatherDay(
            temperatureCelsius = this.day.temperatureCelsius,
            descriptionWeather = DescriptionWeather(
                descriptionWeather = this.day.descriptionWeather.text,
                iconWeather = this.day.descriptionWeather.icon,
            ),
            windKph = this.day.windKph,
            maxWindKph = this.day.maxWindKph
        )
    )
}
