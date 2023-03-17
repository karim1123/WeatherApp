package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.ui.R
import karim.gabbasov.forecast.model.HourlyWeatherData
import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.util.WeatherCondition
import karim.gabbasov.ui.model.WeatherType

@Composable
internal fun HourlyWeatherForecast(
    hourlyWeather: List<HourlyWeatherData>,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        LazyRow(content = {
            items(hourlyWeather) { weatherData ->
                when (weatherData) {
                    is HourlyWeatherData.ElementsOfWeather -> ElementsOfWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(110.dp),
                        textColor = textColor,
                        secondTextColor = secondTextColor
                    )
                    is HourlyWeatherData.CurrentHour -> CurrentHourWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(110.dp)
                            .padding(horizontal = 16.dp),
                        textColor = textColor,
                        secondTextColor = secondTextColor
                    )
                    is HourlyWeatherData.FirstHourOfNewDay -> FirstHourOfNewDayWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(110.dp)
                            .padding(horizontal = 16.dp),
                        textColor = textColor,
                        secondTextColor = secondTextColor
                    )
                    is HourlyWeatherData.DefaultHour -> HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(110.dp)
                            .padding(horizontal = 16.dp),
                        textColor = textColor,
                        secondTextColor = secondTextColor
                    )
                }
            }
        })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewHourlyWeatherForecast() {
    HourlyWeatherForecast(
        textColor = White,
        secondTextColor = LightGrey,
        hourlyWeather = listOf(
            HourlyWeatherData.ElementsOfWeather(
                windSpeed = 8,
                pressure = 1019,
                humidity = 70
            ),
            HourlyWeatherData.CurrentHour(
                temperature = 12,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 13,
                temperature = 12,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 14,
                temperature = 11,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 15,
                temperature = 10,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 16,
                temperature = 9,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 17,
                temperature = 8,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 18,
                temperature = 8,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 19,
                temperature = 8,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 20,
                temperature = 7,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 21,
                temperature = 7,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 22,
                temperature = 6,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.DefaultHour(
                hour = 23,
                temperature = 4,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            ),
            HourlyWeatherData.FirstHourOfNewDay(
                dayOfMonth = 14,
                monthName = MonthNames.FEBRUARY,
                temperature = 4,
                weatherType = WeatherType(
                    weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                    iconRes = R.drawable.cloudy_3_day
                )
            )
        )
    )
}
