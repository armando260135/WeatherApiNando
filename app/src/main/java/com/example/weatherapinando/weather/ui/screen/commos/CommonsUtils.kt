package com.example.weatherapinando.weather.ui.screen.commos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

internal object CommonsUtils {

    const val DATE_FORMAT = "EEEE, d 'de' MMMM"
    const val TEXT_NEUTRAL_WEATHER = "--"
    const val TEXT_HTTPS = "https:"
    const val TEXT_TEMPERATURE_NEUTRAL = "--°C"
    const val TEXT_TEMPERATURE_CELSIUS = "°C"
    const val COUNTRY_INITIAL = "Bogota"

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getFormattedDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(
            DATE_FORMAT,
            Locale.getDefault()
        )

        return currentDate.format(formatter).replaceFirstChar { it.uppercase() }
    }
}