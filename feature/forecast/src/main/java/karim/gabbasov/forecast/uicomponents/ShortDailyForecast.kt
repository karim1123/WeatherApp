package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.forecast.model.ShortForecastForDay
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.util.WeatherCondition

@Composable
internal fun ShortDailyForecast(
    dailyData: List<ShortDailyWeatherData>,
    onForecastDetails: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(R.string.short_forecast_title),
            color = WeatherAppTheme.colors.dailyForecastText,
            style = WeatherAppTheme.typography.topBarTitle
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp),
            userScrollEnabled = false,
            content = {
                itemsIndexed(dailyData) { index, weatherData ->
                    when (weatherData) {
                        is ShortDailyWeatherData.CommonDay -> ShorForecastForDay(
                            weatherData = weatherData.forecast,
                            textColor = WeatherAppTheme.colors.dailyForecastText,
                            secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
                            dayOfWeekColor = WeatherAppTheme.colors.dailyForecastText,
                            index = index,
                            onForecastDetails = onForecastDetails
                        )
                        is ShortDailyWeatherData.Weekend -> ShorForecastForDay(
                            weatherData = weatherData.forecast,
                            textColor = WeatherAppTheme.colors.dailyForecastText,
                            secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
                            dayOfWeekColor = LightRed,
                            index = index,
                            onForecastDetails = onForecastDetails
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun ShorForecastForDay(
    weatherData: ShortForecastForDay,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondaryTextColor: Color,
    dayOfWeekColor: Color,
    index: Int,
    onForecastDetails: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onForecastDetails(index) },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = WeatherAppTheme.colors.dailyForecastCard
        )
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .height(60.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "${weatherData.dayOfMonth} " +
                        stringResource(weatherData.monthName.monthNameId),
                    color = secondaryTextColor,
                    style = WeatherAppTheme.typography.smallTitle
                )
                Text(
                    text = stringResource(weatherData.dayOfWeek.dayOfWeekNameId),
                    color = dayOfWeekColor,
                    style = WeatherAppTheme.typography.mediumTitle
                )
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.width(30.dp),
                    imageVector = ImageVector.vectorResource(weatherData.weatherType.iconRes),
                    contentDescription = stringResource(
                        weatherData.weatherType.weatherCondition.stringResId
                    )
                )
                Temperature(
                    title = stringResource(R.string.max),
                    value = stringResource(
                        R.string.temperature_in_celsius,
                        weatherData.maxTemperature
                    ),
                    textColor = textColor,
                    secondaryTextColor = secondaryTextColor
                )
                Temperature(
                    title = stringResource(R.string.min),
                    value = stringResource(
                        R.string.temperature_in_celsius,
                        weatherData.minTemperature
                    ),
                    textColor = secondaryTextColor,
                    secondaryTextColor = secondaryTextColor
                )
            }
        }
    }
}

@Composable
private fun Temperature(
    title: String,
    value: String,
    textColor: Color,
    secondaryTextColor: Color,
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = secondaryTextColor,
            style = WeatherAppTheme.typography.smallTitle
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = textColor,
            style = WeatherAppTheme.typography.value
        )
    }
}

@Preview
@Composable
private fun PreviewShortDailyForecast() {
    ShortDailyForecast(
        dailyData = listOf(
            ShortDailyWeatherData.CommonDay(
                forecast = ShortForecastForDay(
                    dayOfMonth = 12,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.MONDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            ),
            ShortDailyWeatherData.CommonDay(
                forecast = ShortForecastForDay(
                    dayOfMonth = 13,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.TUESDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            ),
            ShortDailyWeatherData.CommonDay(
                forecast = ShortForecastForDay(
                    dayOfMonth = 14,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.THURSDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            ),
            ShortDailyWeatherData.CommonDay(
                forecast = ShortForecastForDay(
                    dayOfMonth = 15,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.FRIDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            ),
            ShortDailyWeatherData.Weekend(
                forecast = ShortForecastForDay(
                    dayOfMonth = 16,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.SATURDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            ),
            ShortDailyWeatherData.Weekend(
                forecast = ShortForecastForDay(
                    dayOfMonth = 17,
                    monthName = MonthNames.APRIL,
                    dayOfWeek = DayOfWeekNames.SUNDAY,
                    weatherType = WeatherType(
                        weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                        iconRes = R.drawable.cloudy_3_day
                    ),
                    maxTemperature = 10,
                    minTemperature = 3
                )
            )
        ),
        onForecastDetails = {}
    )
}

@Preview
@Composable
private fun PreviewShorForecastForDay() {
    val weatherData = ShortForecastForDay(
        dayOfMonth = 12,
        monthName = MonthNames.APRIL,
        dayOfWeek = DayOfWeekNames.SUNDAY,
        weatherType = WeatherType(
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            iconRes = R.drawable.cloudy_3_day
        ),
        maxTemperature = 10,
        minTemperature = 3
    )
    ShorForecastForDay(
        weatherData = weatherData,
        textColor = WeatherAppTheme.colors.dailyForecastText,
        secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
        dayOfWeekColor = WeatherAppTheme.colors.dailyForecastText,
        index = 0,
        onForecastDetails = {}
    )
}

@Preview
@Composable
private fun PreviewTemperature() {
    Temperature(
        title = stringResource(R.string.max),
        value = stringResource(
            R.string.temperature_in_celsius,
            10
        ),
        textColor = WeatherAppTheme.colors.dailyForecastText,
        secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText
    )
}
