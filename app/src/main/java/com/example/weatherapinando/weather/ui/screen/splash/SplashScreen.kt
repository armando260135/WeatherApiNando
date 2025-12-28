package com.example.weatherapinando.weather.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapinando.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToHomeScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.splash_gradient_dark),
                        colorResource(R.color.splash_gradient_dark_blue),
                        colorResource(R.color.splash_gradient_medium_blue),
                        colorResource(R.color.splash_gradient_night_blue)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                LottieSplashAnimation()
                Spacer(modifier = Modifier.height(24.dp))
                TittleText()
                Spacer(modifier = Modifier.height(12.dp))
                DescriptionText()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    LaunchedEffect(Unit) {
        dealyAndNavigate(3000, navigateToHomeScreen)
    }
}

private suspend fun dealyAndNavigate(timeMillis: Long, screenDestination: () -> Unit) {
    delay(timeMillis)
    screenDestination()
}


@Composable
private fun LottieSplashAnimation(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation_splash)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
            .size(240.dp)
            .widthIn(max = 240.dp)
    )
}

@Composable
private fun TittleText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.splash_title),
        fontSize = 46.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.splash_description),
        fontSize = 22.sp,
        color = Color(0xFFE0E0E0),
        textAlign = TextAlign.Center,

        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen({})
}