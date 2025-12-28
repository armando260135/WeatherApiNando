package com.example.weatherapinando.ui.viewmodel.ui

import androidx.lifecycle.MutableLiveData
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.ActuallyWeather
import com.example.weatherapinando.weather.domain.models.ListDaysWeather
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.screen.home.ContentUIHomeScreen
import com.example.weatherapinando.weather.ui.viewmodels.SearchViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SnapShotTesting {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun previewHomeScreen() {
        val mockSearchViewModel = mockk<SearchViewModel>(relaxed = true)

        val mockLiveData = MutableLiveData<WeatherResultRepository<List<Location>>>()

        every { mockSearchViewModel.countryResults } returns mockLiveData

        paparazzi.snapshot {
            ContentUIHomeScreen(
                weatherDetailInitial = WeatherDetails(
                    location = Location(
                        locationSearch = "Madrid",
                        country = "Spain"
                    ),
                    actualWeather = ActuallyWeather(
                        currentTemperatureC = 24.5,
                        windInKph = 12.0,
                        descriptionWeather = com.example.weatherapinando.weather.domain.models.DescriptionWeather(
                            descriptionWeather = "Sunny",
                            iconWeather = "//cdn.weatherapi.com/weather/64x64/day/113.png"
                        ),
                        lastUpdated = "2024-12-28 15:30"
                    ),
                    listDaysWeather = ListDaysWeather(
                        listDaysWeather = emptyList()
                    )
                ),
                viewModelSearch = mockSearchViewModel,
                onCitySelected = {},
            )
        }
    }
}