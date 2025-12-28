package com.example.weatherapinando.weather.ui.screen.commos

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapinando.R

@Composable
internal fun ImageAsync(iconUrl: String, modifier: Modifier) {
    AsyncImage(
        model = iconUrl,
        contentDescription = "",
        error = painterResource(R.drawable.placeholder_image_initial),
        modifier = modifier,
        contentScale = ContentScale.Fit
    )

    Spacer(modifier = Modifier.height(16.dp))
}