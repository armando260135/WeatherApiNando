package com.example.weatherapinando.weather.ui.screen.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapinando.R
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.Location
import com.example.weatherapinando.weather.ui.viewmodels.SearchViewModel

@Composable
fun SearchScreen(
    searchParam: String,
    viewModelSearch: SearchViewModel,
    onCitySelected: (String) -> Unit
) {
    val countryResultsState by viewModelSearch.countryResults.observeAsState(initial = null)
    var textFieldValue by remember { mutableStateOf(searchParam) }

    LaunchedEffect(textFieldValue) {
        if (textFieldValue.length >= 2) {
            viewModelSearch.searchWeatherForLocationCustom(textFieldValue)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SearchBarField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                onClearValue = {
                    textFieldValue = ""
                    viewModelSearch.clearSearchResults()
                }
            )

            SearchResultState(
                state = countryResultsState,
                onCitySelected = { fullCityName ->
                    textFieldValue = fullCityName
                    onCitySelected(fullCityName)
                    viewModelSearch.clearSearchResults()
                }
            )
        }
    }
}

@Composable
private fun SearchResultState(
    state: WeatherResultRepository<List<Location>>?,
    onCitySelected: (String) -> Unit
) {
    if (state == null) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 4.dp,
                start = 8.dp,
                end = 8.dp
            )
            .heightIn(max = 400.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B3B5A)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        when (state) {
            is WeatherResultRepository.Success -> {
                val cities = state.data.map {
                    val name = it.locationSearch
                    val country = it.country
                    "$name, $country"
                }
                Log.i("armando", state.data.toString())

                if (cities.isNotEmpty()) {
                    SearchSuccessList(
                        locations = cities,
                        onCitySelected = onCitySelected
                    )
                } else {
                    EmptySearchMessage()
                }
            }

            is WeatherResultRepository.Error -> {
                SearchError(message = state.message)
            }
        }
    }
}


@Composable
private fun EmptySearchMessage() {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No results found", color = Color.White.copy(alpha = 0.6f))
    }
}

@Composable
private fun SearchSuccessList(
    locations: List<String>,
    onCitySelected: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(locations) { cityString ->
            LocationItem(
                location = cityString,
                onClick = { onCitySelected(cityString) }
            )
            HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
        }
    }
}

@Composable
private fun LocationItem(location: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp, horizontal = 16.dp)
    ) {
        Text(
            text = location,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SearchBarField(
    value: String,
    onValueChange: (String) -> Unit,
    onClearValue: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 70.dp),
        shape = RoundedCornerShape(20.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                color = Color.White.copy(alpha = 0.6f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_contentdesc),
                tint = Color.White.copy(alpha = 0.85f)
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onClearValue() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White.copy(alpha = 0.8f)
        ),
        singleLine = true,
        maxLines = 1
    )
}


@Composable
private fun SearchError(message: String) {
    Text(
        text = message,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth()
    )
}