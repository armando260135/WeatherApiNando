package com.example.weatherapinando.weather.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapinando.weather.domain.GetWeatherDetail
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.COUNTRY_INITIAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherForLocation: GetWeatherDetail
) : ViewModel() {

    private val _locationWeather = MutableLiveData<WeatherResultRepository<WeatherDetails>>()
    val locationsWeather: LiveData<WeatherResultRepository<WeatherDetails>> get() = _locationWeather

    fun initialWeather(country: String = COUNTRY_INITIAL) {
        fetchWeather(country)
    }

    fun searchWeatherForLocationCustom(country: String) {
        fetchWeather(country)
    }

    private fun fetchWeather(country: String) {
        viewModelScope.launch {
            try {
                val result = weatherForLocation(country)
                _locationWeather.value = result

            } catch (e: Exception) {
                _locationWeather.value = WeatherResultRepository.Error("Error", e)
            }
        }
    }
}

