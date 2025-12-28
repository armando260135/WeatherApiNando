package com.example.weatherapinando.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.foundation.layout.size
import com.example.weatherapinando.weather.domain.GetResultLocations
import com.example.weatherapinando.weather.domain.GetWeatherDetail
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.viewmodels.HomeScreenViewModel
import com.example.weatherapinando.weather.ui.viewmodels.SearchViewModel
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
class SearchComponentViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var getResultLocations: GetResultLocations
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getResultLocations = mockk()
        viewModel = SearchViewModel(getResultLocations)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchWeatherForLocationCustom should emit Success when city is found`() {
        // GIVEN
        val query = "Madrid"
        val mockLocations = listOf(
            Location(locationSearch = "Madrid", country = "Spain"),
            Location(locationSearch = "Madrid", country = "Colombia")
        )
        val expectedResult = WeatherResultRepository.Success(mockLocations)

        coEvery { getResultLocations(query) } returns expectedResult

        // WHEN
        viewModel.searchWeatherForLocationCustom(query)

        // THEN
        val state = viewModel.countryResults.value
        assert(state is WeatherResultRepository.Success)
        assert((state as WeatherResultRepository.Success).data.size == 2)
        assert(state.data[0].locationSearch == "Madrid")
        assert(state.data[0].country == "Spain")
    }

    @Test
    fun `searchWeatherForLocationCustom should emit Error when API fails`() {
        // GIVEN
        val query = "Unknown"
        val expectedError = WeatherResultRepository.Error("City not found", Exception())

        coEvery { getResultLocations(query) } returns expectedError

        // WHEN
        viewModel.searchWeatherForLocationCustom(query)

        // THEN
        val state = viewModel.countryResults.value
        assert(state is WeatherResultRepository.Error)
        assert((state as WeatherResultRepository.Error).message == "City not found")
    }

    @Test
    fun `searchWeatherForLocationCustom should not trigger search when query is blank`() {
        // WHEN
        viewModel.searchWeatherForLocationCustom("   ")

        // THEN
        val state = viewModel.countryResults.value
        assert(state == null)
    }

    @Test
    fun `clearSearchResults should set countryResults to null`() {
        // GIVEN: A state with some data
        val mockLocations = listOf(mockk<Location>())
        coEvery { getResultLocations(any()) } returns WeatherResultRepository.Success(mockLocations)
        viewModel.searchWeatherForLocationCustom("London")

        // WHEN
        viewModel.clearSearchResults()

        // THEN
        val state = viewModel.countryResults.value
        assert(state == null)
    }
}