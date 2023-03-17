package karim.gabbasov.detailed_forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.detailed_forecast.model.DisplayableDailyWeatherDataByTimeOfDay
import karim.gabbasov.detailed_forecast.util.PartsOfDay
import karim.gabbasov.detailed_forecast.util.PartsOfDay.Companion.getOfPartOfDayResIdByIndex
import karim.gabbasov.ui.R
import karim.gabbasov.ui.R.string.feels_like
import karim.gabbasov.ui.R.string.humidity_title
import karim.gabbasov.ui.R.string.pressure_in_hpa
import karim.gabbasov.ui.R.string.pressure_title
import karim.gabbasov.ui.R.string.temperature_in_celsius
import karim.gabbasov.ui.R.string.wind_speed_in_km
import karim.gabbasov.ui.R.string.wind_title
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.WeatherCondition
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val PERCENT = "%"

@Composable
internal fun DailyTemperature(dailyForecast: DisplayableDailyWeatherDataByTimeOfDay) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherAppTheme.colors.dailyForecastCard)
            .padding(vertical = 10.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            itemsIndexed(dailyForecast.temperature) { index, temperature ->
                DailyTemperatureItem(
                    partOfDayResId = getOfPartOfDayResIdByIndex(index),
                    temperature = temperature,
                    weatherType = dailyForecast.weatherType[index]
                )
            }
        }
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(feels_like),
                color = WeatherAppTheme.colors.dailyForecastSecondText,
                style = WeatherAppTheme.typography.smallTitle
            )
            Divider(
                modifier = Modifier.padding(start = 20.dp),
                color = WeatherAppTheme.colors.dailyForecastSecondText,
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            items(dailyForecast.feelsLike) { feelsLike ->
                Text(
                    text = stringResource(temperature_in_celsius, feelsLike),
                    textAlign = TextAlign.Center,
                    color = WeatherAppTheme.colors.dailyForecastSecondText,
                    style = WeatherAppTheme.typography.value
                )
            }
        }
    }
}

@Composable
private fun DailyTemperatureItem(
    partOfDayResId: Int,
    temperature: Int,
    weatherType: WeatherType
) {
    Column(
        modifier = Modifier.width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(partOfDayResId),
            color = WeatherAppTheme.colors.dailyForecastSecondText,
            style = WeatherAppTheme.typography.smallTitle
        )
        Image(
            modifier = Modifier.size(40.dp),
            imageVector = ImageVector.vectorResource(weatherType.iconRes),
            contentDescription = stringResource(weatherType.weatherCondition.stringResId)
        )
        Text(
            text = stringResource(temperature_in_celsius, temperature),
            color = WeatherAppTheme.colors.dailyForecastText,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Composable
internal fun DailyWindSpeed(windSpeed: ImmutableList<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherAppTheme.colors.dailyForecastCard)
            .padding(vertical = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(wind_title),
            style = WeatherAppTheme.typography.topBarTitle,
            color = WeatherAppTheme.colors.dailyForecastSecondText
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            itemsIndexed(windSpeed) { index, windSpeed ->
                DailyWindSpeedItem(
                    partOfDayResId = getOfPartOfDayResIdByIndex(index),
                    windSpeed = windSpeed
                )
            }
        }
    }
}

@Composable
private fun DailyWindSpeedItem(
    partOfDayResId: Int,
    windSpeed: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(partOfDayResId),
            color = WeatherAppTheme.colors.dailyForecastSecondText,
            style = WeatherAppTheme.typography.smallTitle
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(
                text = "$windSpeed ",
                color = WeatherAppTheme.colors.dailyForecastText,
                style = WeatherAppTheme.typography.value
            )
            Text(
                text = stringResource(wind_speed_in_km, windSpeed),
                color = WeatherAppTheme.colors.dailyForecastText,
                style = WeatherAppTheme.typography.value
            )
        }
    }
}

@Composable
internal fun DailyHumidity(humidity: ImmutableList<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherAppTheme.colors.dailyForecastCard)
            .padding(vertical = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(humidity_title),
            style = WeatherAppTheme.typography.topBarTitle,
            color = WeatherAppTheme.colors.dailyForecastSecondText
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            itemsIndexed(humidity) { index, humidity ->
                DailyHumidityItem(
                    partOfDayResId = getOfPartOfDayResIdByIndex(index),
                    humidity = humidity
                )
            }
        }
    }
}

@Composable
private fun DailyHumidityItem(
    partOfDayResId: Int,
    humidity: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(partOfDayResId),
            color = WeatherAppTheme.colors.dailyForecastSecondText,
            style = WeatherAppTheme.typography.smallTitle
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$humidity$PERCENT",
            color = WeatherAppTheme.colors.dailyForecastText,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Composable
internal fun DailyPressure(pressure: ImmutableList<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherAppTheme.colors.dailyForecastCard)
            .padding(vertical = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(pressure_title),
            style = WeatherAppTheme.typography.topBarTitle,
            color = WeatherAppTheme.colors.dailyForecastSecondText
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false
        ) {
            itemsIndexed(pressure) { index, pressure ->
                DailyPressureItem(
                    partOfDayResId = getOfPartOfDayResIdByIndex(index),
                    pressure = pressure
                )
            }
        }
    }
}

@Composable
private fun DailyPressureItem(
    partOfDayResId: Int,
    pressure: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(partOfDayResId),
            color = WeatherAppTheme.colors.dailyForecastSecondText,
            style = WeatherAppTheme.typography.smallTitle
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = pressure.toString(),
                color = WeatherAppTheme.colors.dailyForecastText,
                style = WeatherAppTheme.typography.value
            )
            Text(
                text = stringResource(pressure_in_hpa),
                color = WeatherAppTheme.colors.dailyForecastSecondText,
                style = WeatherAppTheme.typography.smallTitle
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDailyTemperature() {
    val data = DisplayableDailyWeatherDataByTimeOfDay(
        temperature = persistentListOf(3, 10, 5, 2),
        feelsLike = persistentListOf(1, 8, 3, -1),
        windSpeed = persistentListOf(),
        pressure = persistentListOf(),
        humidity = persistentListOf(),
        weatherType = persistentListOf(
            WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            ),
            WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            ),
            WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            ),
            WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_night
            )
        ),
        tabData = Pair(12, DayOfWeekNames.FRIDAY)
    )
    DailyTemperature(data)
}

@Preview
@Composable
private fun PreviewDailyTemperatureItem() {
    DailyTemperatureItem(
        partOfDayResId = PartsOfDay.MORNING.partOfDayResId,
        temperature = 10,
        weatherType = WeatherType(
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            iconRes = R.drawable.cloudy_3_day
        )
    )
}

@Preview
@Composable
private fun PreviewDailyWindSpeed() {
    DailyWindSpeed(persistentListOf(8, 8, 7, 6))
}

@Preview
@Composable
private fun PreviewDailyWindSpeedItem() {
    DailyWindSpeedItem(PartsOfDay.MORNING.partOfDayResId, 8)
}

@Preview
@Composable
private fun PreviewDailyHumidity() {
    DailyHumidity(persistentListOf(84, 80, 73, 91))
}

@Preview
@Composable
private fun PreviewDailyHumidityItem() {
    DailyHumidityItem(PartsOfDay.MORNING.partOfDayResId, 44)
}

@Preview
@Composable
private fun PreviewDailyPressure() {
    DailyPressure(persistentListOf(1031, 1035, 1038, 1026))
}

@Preview
@Composable
private fun PreviewDailyPressureItem() {
    DailyPressureItem(PartsOfDay.MORNING.partOfDayResId, 1031)
}
