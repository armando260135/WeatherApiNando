package com.example.weatherapinando.weather.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapinando.weather.domain.GetResultLocations
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val resultsCountries: GetResultLocations,
) : ViewModel() {

    private val _countryResults = MutableLiveData<WeatherResultRepository<List<Location>>>()
    val countryResults: LiveData<WeatherResultRepository<List<Location>>> get() = _countryResults

    fun searchWeatherForLocationCustom(wordSearch: String) {
        if (wordSearch.isBlank()) return

        viewModelScope.launch {
            val result = resultsCountries(wordSearch)
            _countryResults.value = result
        }
    }

    fun clearSearchResults() {
        _countryResults.value = null
    }
}