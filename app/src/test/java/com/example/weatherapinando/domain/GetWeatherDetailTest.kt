package com.example.weatherapinando.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapinando.weather.domain.GetWeatherDetail
import com.example.weatherapinando.weather.domain.WeatherRepository
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.viewmodels.HomeScreenViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherDetailTest {

    private lateinit var repository: WeatherRepository
    private lateinit var getWeatherDetail: GetWeatherDetail

    @Before
    fun setup() {
        repository = mockk()
        getWeatherDetail = GetWeatherDetail(repository)
    }

    @Test
    fun `invoke should call repository and return success`() = runTest {
        // GIVEN
        val cityName = "Madrid"
        val mockWeather = mockk<WeatherDetails>()
        coEvery { repository.getWeatherByCity(cityName) } returns WeatherResultRepository.Success(
            mockWeather
        )

        // WHEN
        val result = getWeatherDetail(cityName)

        // THEN
        assert(result is WeatherResultRepository.Success)
        assert((result as WeatherResultRepository.Success).data == mockWeather)
    }
}