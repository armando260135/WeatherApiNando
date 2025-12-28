package com.example.weatherapinando.weather.data.datasource.mapper

import com.example.weatherapinando.weather.data.datasource.ForecastDay
import com.example.weatherapinando.weather.data.datasource.ForecastResponse
import com.example.weatherapinando.weather.domain.models.ActuallyWeather
import com.example.weatherapinando.weather.domain.models.DescriptionWeather
import com.example.weatherapinando.weather.domain.models.DetailWeatherDay
import com.example.weatherapinando.weather.domain.models.ListDaysWeather
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDayDetail
import com.example.weatherapinando.weather.domain.models.WeatherDetails

fun ForecastResponse.toDomain(): WeatherDetails {
    return WeatherDetails(
        location = Location(
            locationSearch = this.location.name, country = this.location.country
        ),
        actualWeather = ActuallyWeather(
            currentTemperatureC = this.current.tempC,
            windInKph = this.current.windKph,
            descriptionWeather = DescriptionWeather(
                descriptionWeather = this.current.condition.text,
                iconWeather = this.current.condition.icon,
            ),
            lastUpdated = this.current.lastUpdated
        ),
        listDaysWeather = ListDaysWeather(
            listDaysWeather = this.forecast.forecastday.map { it.toDomain() }
        )
    )
}

fun ForecastDay.toDomain(): WeatherDayDetail {
    return WeatherDayDetail(
        date = this.date,
        detailWeatherDay = DetailWeatherDay(
            temperatureCelsius = this.day.avgTempC,
            descriptionWeather = DescriptionWeather(
                descriptionWeather = this.day.condition.text,
                iconWeather = this.day.condition.icon,
            ),
            windKph = this.day.windKph,
            maxWindKph = this.day.maxwind_kph
        )
    )
}
