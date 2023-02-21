package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.ui.R
import karim.gabbasov.ui.R.string.hour
import karim.gabbasov.ui.R.string.humidity_in_percent
import karim.gabbasov.ui.R.string.now
import karim.gabbasov.ui.R.string.pressure_in_hpa
import karim.gabbasov.ui.R.string.temperature_in_celsius
import karim.gabbasov.ui.R.string.wind_speed_in_km
import karim.gabbasov.ui.model.HourlyWeatherData
import karim.gabbasov.ui.model.MonthNames
import karim.gabbasov.ui.model.WeatherCondition
import karim.gabbasov.ui.model.WeatherType

const val FIRST_HOUR_OF_NEW_DAY = 0
const val PERCENT = "%"

@Composable
fun HourlyWeatherDisplay(
    weatherData: HourlyWeatherData.DefaultHour,
    modifier: Modifier = Modifier,
    textColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(hour, weatherData.hour),
            color = textColor
        )
        Image(
            imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
            contentDescription = stringResource(
                weatherData.weatherType.weatherCondition.stringResId
            ),
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = stringResource(temperature_in_celsius, weatherData.temperature),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ElementsOfWeatherDisplay(
    weatherData: HourlyWeatherData.ElementsOfWeather,
    modifier: Modifier = Modifier,
    textColor: Color
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherDataDisplay(
            value = stringResource(wind_speed_in_km, weatherData.windSpeed),
            icon = ImageVector.vectorResource(R.drawable.ic_wind),
            iconTint = Color.White,
            textStyle = TextStyle(color = textColor)
        )
        WeatherDataDisplay(
            value = stringResource(pressure_in_hpa, weatherData.pressure),
            icon = ImageVector.vectorResource(R.drawable.ic_pressure),
            iconTint = Color.White,
            textStyle = TextStyle(color = textColor)
        )
        WeatherDataDisplay(
            value = stringResource(humidity_in_percent, weatherData.humidity, PERCENT),
            icon = ImageVector.vectorResource(R.drawable.ic_drop),
            iconTint = Color.White,
            textStyle = TextStyle(color = textColor)
        )
    }
}

@Composable
fun CurrentHourWeatherDisplay(
    weatherData: HourlyWeatherData.CurrentHour,
    modifier: Modifier = Modifier,
    textColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(now),
            color = textColor
        )
        Image(
            imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = stringResource(temperature_in_celsius, weatherData.temperature),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FirstHourOfNewDayWeatherDisplay(
    weatherData: HourlyWeatherData.FirstHourOfNewDay,
    modifier: Modifier = Modifier,
    textColor: Color
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
                text = stringResource(hour, FIRST_HOUR_OF_NEW_DAY),
                color = textColor
            )
            Text(
                text =
                "${weatherData.dayOfMonth} ${stringResource(weatherData.monthName.stringResId)}",
                color = textColor
            )
            Image(
                imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
        }
        Text(
            text = stringResource(temperature_in_celsius, weatherData.temperature),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
fun PreviewHourlyWeatherDisplay() {
    HourlyWeatherDisplay(
        weatherData = HourlyWeatherData.DefaultHour(
            hour = 0,
            temperature = 3,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
        textColor = Color.White
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
fun PreviewElementsOfWeatherDisplay() {
    ElementsOfWeatherDisplay(
        weatherData = HourlyWeatherData.ElementsOfWeather(
            windSpeed = 8,
            pressure = 922,
            humidity = 78
        ),
        textColor = Color.White
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
fun PreviewCurrentHourWeatherDisplay() {
    CurrentHourWeatherDisplay(
        weatherData = HourlyWeatherData.CurrentHour(
            temperature = 10,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
        textColor = Color.White
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
fun PreviewFirstHourOfNewDayWeatherDisplay() {
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
        textColor = Color.White
    )
}
