package com.example.weatherapinando.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.home_color_box_blue),
                        colorResource(R.color.home_color_box_white)
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.animation_birds)
            )

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderTopBar()
            TodayTitle()
            SearchBarHome()
            Spacer(modifier = Modifier.height(16.dp))
            WeatherImageMain()
            WeatherDate()
            WeatherTemperature()
            Spacer(modifier = Modifier.height(20.dp))
            WeatherInfoRow()
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun HeaderTopBar() {
    val contextDescription = stringResource(R.string.location_info)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            color = colorResource(R.color.home_header_color_box),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(R.string.menu),
                tint = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.semantics(mergeDescendants = true) {
                contentDescription = contextDescription
            }) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = stringResource(R.string.location),
                tint = colorResource(R.color.home_header_icon_color).copy(alpha = 0.9f),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.location_info),
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun TodayTitle() {
    Text(
        text = stringResource(R.string.about_today),
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        modifier = Modifier
            .padding(top = 16.dp, bottom = 26.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
fun WeatherImageMain() {
    Image(
        painter = painterResource(id = R.drawable.sol_nube),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .aspectRatio(1f)

    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun WeatherDate() {
    Text(
        text = getFormattedDate(),
        color = Color.White.copy(alpha = 0.7f),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 10.dp)
    )
    Spacer(modifier = Modifier.height(6.dp))
}


@Composable
fun getFormattedDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern(
        "EEEE, d 'de' MMMM",
        Locale.getDefault()
    )

    return currentDate.format(formatter).replaceFirstChar { it.uppercase() }
}

@Composable
fun WeatherTemperature() {
    Text(
        text = stringResource(R.string.degrees_sample),
        color = Color.White,
        fontSize = 72.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun WeatherInfoRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WeatherInfoCard(
            icon = R.drawable.ic_weather_launcher_foreground,
            title = stringResource(R.string.today_value),
            subtitle = stringResource(R.string.today_label)
        )
        WeatherInfoCard(
            icon = R.drawable.ic_weather_launcher_foreground,
            title = stringResource(R.string.tomorrow_value),
            subtitle = stringResource(R.string.tomorrow_label)
        )
        WeatherInfoCard(
            icon = R.drawable.ic_weather_launcher_foreground,
            title = stringResource(R.string.pas_tomorrow_value),
            subtitle = stringResource(R.string.pas_tomorrow_label)

        )
    }
}


@Composable
fun SearchBarHome() {
    var search by remember { mutableStateOf("") }

    OutlinedTextField(
        value = search,
        onValueChange = { search = it },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 70.dp),
        shape = RoundedCornerShape(20.dp),
        placeholder = {
            Text(
                stringResource(R.string.search_placeholder),
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
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun WeatherInfoCard(icon: Int, title: String, subtitle: String) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth(),
        color = Color(0x661E253B),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(subtitle, color = Color(0xFFCDD0FC), fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}