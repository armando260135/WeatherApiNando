package com.example.weatherapinando.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapinando.weather.domain.GetWeatherDetail
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.viewmodels.HomeScreenViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    // Mocks
    private lateinit var getWeatherDetail: GetWeatherDetail
    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getWeatherDetail = mockk()
        viewModel = HomeScreenViewModel(getWeatherDetail)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initialWeather should emit Success when use case returns data`() {
        // GIVEN
        val mockWeather = mockk<WeatherDetails>()
        val successResult = WeatherResultRepository.Success(mockWeather)

        // Mocking the suspend function call
        coEvery { getWeatherDetail(any()) } returns successResult

        // WHEN
        viewModel.initialWeather("Madrid")

        // THEN
        val result = viewModel.locationsWeather.value
        assert(result is WeatherResultRepository.Success)
        assert((result as WeatherResultRepository.Success).data == mockWeather)
    }

    @Test
    fun `initialWeather should emit Error when use case returns failure`() {
        // GIVEN
        val exception = Exception("Network Error")
        val errorResult = WeatherResultRepository.Error("Error message", exception)

        coEvery { getWeatherDetail(any()) } returns errorResult

        // WHEN
        viewModel.initialWeather("Madrid")

        // THEN
        val result = viewModel.locationsWeather.value
        assert(result is WeatherResultRepository.Error)
        assert((result as WeatherResultRepository.Error).message == "Error message")
    }
}