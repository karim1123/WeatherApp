package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karim.gabbasov.ui.R
import karim.gabbasov.ui.R.string.feels_like
import karim.gabbasov.ui.R.string.temperature_in_celsius
import karim.gabbasov.ui.model.DisplayableWeatherData
import karim.gabbasov.ui.model.HourlyWeatherData
import karim.gabbasov.ui.model.WeatherCondition
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.theme.DarkBlue

@Composable
fun WeatherForecastCard(
    currentWeather: DisplayableWeatherData,
    hourlyWeather: List<HourlyWeatherData>,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
        modifier = modifier,
        backgroundColor = DarkBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = stringResource(
                        temperature_in_celsius,
                        currentWeather.temperatureCelsius
                    ),
                    color = textColor,
                    fontSize = 50.sp
                )
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    modifier = Modifier.defaultMinSize(minWidth = 90.dp, minHeight = 90.dp),
                    imageVector = ImageVector.vectorResource(
                        currentWeather.weatherType.iconRes
                    ),
                    contentDescription = stringResource(
                        currentWeather.weatherType.weatherCondition.stringResId
                    )
                )
            }
            Text(
                text = stringResource(currentWeather.weatherType.weatherCondition.stringResId),
                color = textColor,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${stringResource(feels_like)} ${
                stringResource(temperature_in_celsius, currentWeather.feelsLike)
                }",
                fontSize = 20.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(16.dp))

            HourlyWeatherForecast(
                hourlyWeather = hourlyWeather,
                textColor = textColor
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
fun PreviewWeatherForecastCard() {
    WeatherForecastCard(
        currentWeather = DisplayableWeatherData(
            hour = 12,
            temperatureCelsius = 11,
            feelsLike = 8,
            windSpeed = 9,
            pressure = 982,
            humidity = 77,
            weatherType = WeatherType(
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                iconRes = R.drawable.cloudy_3_day
            )
        ),
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
            )
        ),
        textColor = Color.White
    )
}
