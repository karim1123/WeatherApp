package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.forecast.model.HourlyWeatherData
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.util.WeatherCondition

private const val FIRST_HOUR_OF_NEW_DAY = 0
private const val PERCENT = "%"

@Composable
internal fun ElementsOfWeatherDisplay(
    weatherData: HourlyWeatherData.ElementsOfWeather,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherDataDisplay(
            value = "${weatherData.windSpeed} ",
            measure = stringResource(R.string.wind_speed_in_km),
            icon = ImageVector.vectorResource(R.drawable.ic_wind),
            textColor = textColor,
            secondTextColor = secondTextColor
        )
        WeatherDataDisplay(
            value = "${weatherData.pressure} ",
            measure = stringResource(R.string.pressure_in_hpa),
            icon = ImageVector.vectorResource(R.drawable.ic_pressure),
            textColor = textColor,
            secondTextColor = secondTextColor
        )
        WeatherDataDisplay(
            value = "${weatherData.humidity}",
            measure = PERCENT,
            icon = ImageVector.vectorResource(R.drawable.ic_drop),
            textColor = textColor,
            secondTextColor = secondTextColor
        )
    }
    Spacer(modifier = Modifier.padding(end = 40.dp))
}

@Composable
internal fun HourlyWeatherDisplay(
    weatherData: HourlyWeatherData.DefaultHour,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.hour, weatherData.hour),
            color = secondTextColor,
            style = WeatherAppTheme.typography.smallTitle
        )
        Image(
            imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
            contentDescription = stringResource(
                weatherData.weatherType.weatherCondition.stringResId
            ),
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = stringResource(R.string.temperature_in_celsius, weatherData.temperature),
            color = textColor,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Composable
internal fun CurrentHourWeatherDisplay(
    weatherData: HourlyWeatherData.CurrentHour,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.now),
            color = secondTextColor,
            style = WeatherAppTheme.typography.smallTitle
        )
        Image(
            imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = stringResource(R.string.temperature_in_celsius, weatherData.temperature),
            color = textColor,
            fontWeight = FontWeight.Bold,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Composable
internal fun FirstHourOfNewDayWeatherDisplay(
    weatherData: HourlyWeatherData.FirstHourOfNewDay,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.hour, FIRST_HOUR_OF_NEW_DAY),
                color = secondTextColor,
                style = WeatherAppTheme.typography.smallTitle
            )
            Text(
                text = "${weatherData.dayOfMonth} " +
                    stringResource(weatherData.monthName.shortMonthNameId),
                color = secondTextColor,
                style = WeatherAppTheme.typography.smallTitle
            )
            Image(
                imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
        }
        Text(
            text = stringResource(R.string.temperature_in_celsius, weatherData.temperature),
            color = textColor,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewHourlyWeatherDisplay() {
    HourlyWeatherDisplay(
        weatherData = HourlyWeatherData.DefaultHour(
            hour = 0,
            temperature = 3,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
        textColor = White,
        secondTextColor = LightGrey
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewElementsOfWeatherDisplay() {
    ElementsOfWeatherDisplay(
        weatherData = HourlyWeatherData.ElementsOfWeather(
            windSpeed = 8,
            pressure = 922,
            humidity = 78
        ),
        textColor = White,
        secondTextColor = LightGrey
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewCurrentHourWeatherDisplay() {
    CurrentHourWeatherDisplay(
        weatherData = HourlyWeatherData.CurrentHour(
            temperature = 10,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
        textColor = White,
        secondTextColor = LightGrey
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewFirstHourOfNewDayWeatherDisplay() {
    FirstHourOfNewDayWeatherDisplay(
        weatherData = HourlyWeatherData.FirstHourOfNewDay(
            dayOfMonth = 14,
            monthName = MonthNames.FEBRUARY,
            temperature = 4,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
        textColor = White,
        secondTextColor = LightGrey
    )
}
