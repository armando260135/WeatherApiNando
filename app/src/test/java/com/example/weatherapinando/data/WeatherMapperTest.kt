package com.example.weatherapinando.data

import com.example.weatherapinando.weather.data.datasource.mapper.toDomain
import com.example.weatherapinando.weather.data.datasource.serializedData.ActuallyWeather
import com.example.weatherapinando.weather.data.datasource.serializedData.Day
import com.example.weatherapinando.weather.data.datasource.serializedData.DescriptionWeather
import com.example.weatherapinando.weather.data.datasource.serializedData.ListWeatherDays
import com.example.weatherapinando.weather.data.datasource.serializedData.Location
import com.example.weatherapinando.weather.data.datasource.serializedData.Weather
import com.example.weatherapinando.weather.data.datasource.serializedData.WeatherDay
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherMapperTest {

    @Test
    fun `toDomain should map API response to Domain model correctly`() {
        val apiResponse = Weather(
            location = Location(
                name = "Madrid",
                country = "Spain"
            ),
            actuallyWeather = ActuallyWeather(
                temperatureCelsius = 25.0,
                windKph = 10.0,
                descriptionWeather = DescriptionWeather(text = "Sunny", icon = "url"),
                lastUpdated = "2023-12-28"
            ),
            forecast = ListWeatherDays(
                listWeatherDays = listOf(
                    WeatherDay(
                        date = "2023-12-29",
                        day = Day(
                            temperatureCelsius = 25.0,
                            windKph = 10.0,
                            descriptionWeather = DescriptionWeather(text = "Sunny", icon = "url"),
                            maxWindKph = 30.00
                        )
                    )
                )
            )
        )

        // WHEN
        val domainModel = apiResponse.toDomain()

        // THEN
        Assert.assertEquals("Madrid", domainModel.location.locationSearch)
        Assert.assertEquals(25.0, domainModel.actualWeather.currentTemperatureC, 0.1)
        Assert.assertEquals(
            "Sunny",
            domainModel.actualWeather.descriptionWeather.descriptionWeather
        )
        // Verifica que la lista también se mapeó (esto era lo que fallaba antes)
        Assert.assertEquals(1, domainModel.listDaysWeather.listDaysWeather.size)
        Assert.assertEquals("2023-12-29", domainModel.listDaysWeather.listDaysWeather[0].date)
    }

}