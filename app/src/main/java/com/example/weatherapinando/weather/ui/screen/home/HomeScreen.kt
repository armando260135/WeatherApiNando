package com.example.weatherapinando.weather.ui.screen.home

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapinando.R
import com.example.weatherapinando.weather.domain.WeatherResultRepository
import com.example.weatherapinando.weather.domain.models.WeatherDetails
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.TEXT_HTTPS
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.TEXT_NEUTRAL_WEATHER
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.TEXT_TEMPERATURE_CELSIUS
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.TEXT_TEMPERATURE_NEUTRAL
import com.example.weatherapinando.weather.ui.screen.commos.CommonsUtils.getFormattedDate
import com.example.weatherapinando.weather.ui.screen.commos.ImageAsync
import com.example.weatherapinando.weather.ui.viewmodels.HomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.initialWeather()
    }

    val weatherState by viewModel.locationsWeather.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = weatherState) {
            is WeatherResultRepository.Success -> {
                ContentStructureHomeScreen(state.data)
            }

            is WeatherResultRepository.Error -> {
                Text(text = state.message)
            }

            else -> {
                // Estado de carga (Loading o null)
                Box(
                    contentAlignment = Alignment.Center,
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
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }
}


@Composable
private fun ContentStructureHomeScreen(weatherDetailInitial: WeatherDetails?) {
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
        AnimationBackground()
        ContentUIHomeScreen(weatherDetailInitial)
    }
}

@Composable
private fun AnimationBackground() {
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
}

@Composable
private fun ContentUIHomeScreen(weatherDetailInitial: WeatherDetails?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTopBar()
        TodayTitle(weatherDetailInitial)
        SearchBarHome()
        Spacer(modifier = Modifier.height(16.dp))
        ConditionTitle(weatherDetailInitial)
        WeatherImageMain(weatherDetailInitial)
        WeatherDate()
        WeatherTemperature(weatherDetailInitial)
        Spacer(modifier = Modifier.height(20.dp))
        WeatherInfoRow(weatherDetailInitial)
        Spacer(modifier = Modifier.height(30.dp))
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
fun TodayTitle(viewModel: WeatherDetails?) {
    Text(
        text = stringResource(
            R.string.about_today,
            viewModel?.location?.locationSearch.orEmpty()
        ),
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
fun ConditionTitle(viewModel: WeatherDetails?) {
    Text(
        text = stringResource(
            R.string.title_weather_temp,
            viewModel?.actualWeather?.descriptionWeather?.descriptionWeather ?: TEXT_NEUTRAL_WEATHER
        ),
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier
            .padding(top = 16.dp, bottom = 26.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun WeatherImageMain(weatherDetailInitial: WeatherDetails?) {
    val iconUrl =
        "$TEXT_HTTPS${weatherDetailInitial?.actualWeather?.descriptionWeather?.iconWeather.orEmpty()}"

    ImageAsync(
        iconUrl = iconUrl, modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .clearAndSetSemantics {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
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
fun WeatherTemperature(weatherDetailInitial: WeatherDetails?) {
    val temperatureTextFinal =
        weatherDetailInitial?.actualWeather?.currentTemperatureC?.toString()
            ?.plus(TEXT_TEMPERATURE_CELSIUS)
            ?: TEXT_TEMPERATURE_NEUTRAL

    Text(
        text = temperatureTextFinal,
        color = Color.White,
        fontSize = 64.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun WeatherInfoRow(weatherDetailInitial: WeatherDetails?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val forecastDays = weatherDetailInitial?.listDaysWeather?.listDaysWeather.orEmpty()
        val labelRes = listOf(
            R.string.today_value,
            R.string.tomorrow_value,
            R.string.pas_tomorrow_value
        )
        val defaultDateText = listOf(
            stringResource(R.string.today_label),
            stringResource(R.string.tomorrow_label),
            stringResource(R.string.pas_tomorrow_label)
        )

        data class Info(
            val iconUrl: String,
            val title: String,
            val dateFormat: String,
            val wind: String
        )

        val items = mutableListOf<Info>()

        for (i in 0..2) {
            if (i == 0) {
                val icon =
                    "https:${weatherDetailInitial?.actualWeather?.descriptionWeather?.iconWeather.orEmpty()}"
                val temp =
                    weatherDetailInitial?.actualWeather?.currentTemperatureC?.toString() ?: "--"
                val date = weatherDetailInitial?.actualWeather?.lastUpdated?.substringBefore(" ")
                    ?: defaultDateText[i]
                val wind = weatherDetailInitial?.actualWeather?.windInKph?.toString() ?: "--"
                val title = stringResource(labelRes[i], temp)
                items += Info(icon, title, date, wind)
            } else {
                val day = forecastDays.getOrNull(i)
                val icon =
                    "https:${day?.detailWeatherDay?.descriptionWeather?.iconWeather.orEmpty()}"
                val temp = day?.detailWeatherDay?.temperatureCelsius?.toString() ?: "--"
                val date = day?.date ?: defaultDateText[i]
                val wind = day?.detailWeatherDay?.maxWindKph?.toString() ?: "--"
                val title = stringResource(labelRes[i], temp)
                items += Info(icon, title, date, wind)
            }
        }

        items.forEach { info ->
            WeatherInfoCard(
                iconUrl = info.iconUrl,
                title = info.title,
                dateFormat = info.dateFormat,
                wind = info.wind
            )
        }
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
fun WeatherInfoCard(iconUrl: String, title: String, dateFormat: String, wind: String) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 2.dp),
        color = Color(0x661E253B),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column {
            DatePill(text = dateFormat)

            Row(modifier = Modifier.fillMaxWidth()) {
                InfoColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    icon = {
                        AsyncImage(
                            model = iconUrl,
                            contentDescription = null,
                            error = painterResource(R.drawable.placeholder_image_initial),
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Fit
                        )
                    },
                    title = title,
                    subtitle = stringResource(R.string.text_temp)
                )

                InfoColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    icon = {
                        Image(
                            painter = painterResource(R.drawable.wind_icon),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Fit
                        )
                    },
                    title = wind,
                    subtitle = stringResource(R.string.text_wind)
                )
            }
        }
    }
}

@Composable
private fun DatePill(text: String) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(start = 12.dp, end = 10.dp)
            .wrapContentWidth()
    ) {
        Text(text, color = Color.Black, fontSize = 14.sp)
    }
}

@Composable
private fun InfoColumn(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(subtitle, color = Color(0xFFCDD0FC), fontSize = 14.sp)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}