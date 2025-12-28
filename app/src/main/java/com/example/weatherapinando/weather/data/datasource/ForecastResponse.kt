package com.example.weatherapinando.weather.data.datasource

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current,
    @SerializedName("forecast") val forecast: Forecast
)

data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)

data class Current(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("last_updated") val lastUpdated: String
)

data class Condition(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String
)

data class Forecast(
    @SerializedName("forecastday") val forecastday: List<ForecastDay>
)

data class ForecastDay(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
    @SerializedName("hour") val hour: List<Hour>
)

data class Day(
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("maxwind_kph") val maxwind_kph: Double,
)

data class Hour(
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("condition") val condition: Condition
)





