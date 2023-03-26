package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.forecast.model.DateData
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.forecast.model.ShortForecastForDay
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.ui.shimmerEffect
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.util.WeatherCondition

private const val DAY_COUNTER = 7

@Composable
internal fun ShortDailyForecast(
    dailyData: List<ShortDailyWeatherData>?,
    isLoading: Boolean,
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
                if (dailyData != null) {
                    itemsIndexed(dailyData) { index, weatherData ->
                        when (weatherData) {
                            is ShortDailyWeatherData.CommonDay -> ShortDailyForecastItem(
                                isLoading = isLoading,
                                weatherData = weatherData.forecast,
                                dateData = weatherData.dateData,
                                textColor = WeatherAppTheme.colors.dailyForecastText,
                                secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
                                dayOfWeekColor = WeatherAppTheme.colors.dailyForecastText,
                                index = index,
                                onForecastDetails = onForecastDetails
                            )
                            is ShortDailyWeatherData.Weekend -> ShortDailyForecastItem(
                                isLoading = isLoading,
                                weatherData = weatherData.forecast,
                                dateData = weatherData.dateData,
                                textColor = WeatherAppTheme.colors.dailyForecastText,
                                secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
                                dayOfWeekColor = LightRed,
                                index = index,
                                onForecastDetails = onForecastDetails
                            )
                        }
                    }
                } else {
                    items(DAY_COUNTER) {
                        ShimmerDailyForecastItem()
                    }
                }
            }
        )
    }
}

@Composable
private fun ShimmerDailyForecastItem() {
    Box(
        modifier = Modifier
            .padding(top = 6.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .shimmerEffect()
    )
}

@Suppress("LongMethod", "LongParameterList")
@Composable
private fun ShortDailyForecastItem(
    isLoading: Boolean,
    weatherData: ShortForecastForDay?,
    dateData: DateData,
    textColor: Color,
    secondaryTextColor: Color,
    dayOfWeekColor: Color,
    index: Int,
    onForecastDetails: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .clickable {
                if (!isLoading) {
                    onForecastDetails(index)
                }
            },
        colors = CardDefaults.cardColors(containerColor = WeatherAppTheme.colors.dailyForecastCard)
    ) {
        Row(
            Modifier
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
                    text = "${dateData.dayOfMonth} " +
                        stringResource(dateData.monthName.monthNameId),
                    color = secondaryTextColor,
                    style = WeatherAppTheme.typography.smallTitle
                )
                Text(
                    text = stringResource(dateData.dayOfWeek.dayOfWeekNameId),
                    color = dayOfWeekColor,
                    style = WeatherAppTheme.typography.mediumTitle
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (weatherData != null && !isLoading) {
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
                } else {
                    ShimmerShortDailyForecastItemContent()
                }
            }
        }
    }
}

@Composable
private fun ShimmerShortDailyForecastItemContent() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmerEffect()
    )
    Box(
        modifier = Modifier
            .height(14.dp)
            .width(30.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmerEffect()

    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(20.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmerEffect()
    )
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
    val weatherData = ShortForecastForDay(
        weatherType = WeatherType(
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            iconRes = R.drawable.cloudy_3_day
        ),
        maxTemperature = 10,
        minTemperature = 3
    )
    val dateData = DateData(
        dayOfMonth = 12,
        monthName = MonthNames.APRIL,
        dayOfWeek = DayOfWeekNames.TODAY,
    )
    val dailyData = listOf(
        ShortDailyWeatherData.CommonDay(
            forecast = weatherData,
            dateData = dateData
        ),
        ShortDailyWeatherData.CommonDay(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 14, dayOfWeek = DayOfWeekNames.TOMORROW)
        ),
        ShortDailyWeatherData.CommonDay(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 15, dayOfWeek = DayOfWeekNames.WEDNESDAY)
        ),
        ShortDailyWeatherData.CommonDay(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 16, dayOfWeek = DayOfWeekNames.THURSDAY)
        ),
        ShortDailyWeatherData.CommonDay(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 17, dayOfWeek = DayOfWeekNames.FRIDAY)
        ),
        ShortDailyWeatherData.Weekend(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 18, dayOfWeek = DayOfWeekNames.SATURDAY)
        ),
        ShortDailyWeatherData.Weekend(
            forecast = weatherData,
            dateData = dateData.copy(dayOfMonth = 18, dayOfWeek = DayOfWeekNames.SUNDAY)
        ),
    )
    ShortDailyForecast(
        isLoading = false,
        dailyData = dailyData,
        onForecastDetails = {}
    )
}

@Preview
@Composable
private fun PreviewShortDailyForecastItem() {
    val weatherData = ShortForecastForDay(
        weatherType = WeatherType(
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            iconRes = R.drawable.cloudy_3_day
        ),
        maxTemperature = 10,
        minTemperature = 3
    )
    val dateData = DateData(
        dayOfMonth = 12,
        monthName = MonthNames.APRIL,
        dayOfWeek = DayOfWeekNames.SUNDAY,
    )
    ShortDailyForecastItem(
        isLoading = false,
        weatherData = weatherData,
        dateData = dateData,
        textColor = WeatherAppTheme.colors.dailyForecastText,
        secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
        dayOfWeekColor = WeatherAppTheme.colors.dailyForecastText,
        index = 0,
        onForecastDetails = {}
    )
}

@Preview
@Composable
private fun PreviewLoadingShortDailyForecastItem() {
    val weatherData = ShortForecastForDay(
        weatherType = WeatherType(
            weatherCondition = WeatherCondition.PARTLY_CLOUDY,
            iconRes = R.drawable.cloudy_3_day
        ),
        maxTemperature = 10,
        minTemperature = 3
    )
    val dateData = DateData(
        dayOfMonth = 12,
        monthName = MonthNames.APRIL,
        dayOfWeek = DayOfWeekNames.SUNDAY,
    )
    ShortDailyForecastItem(
        isLoading = true,
        weatherData = weatherData,
        dateData = dateData,
        textColor = WeatherAppTheme.colors.dailyForecastText,
        secondaryTextColor = WeatherAppTheme.colors.dailyForecastSecondText,
        dayOfWeekColor = WeatherAppTheme.colors.dailyForecastText,
        index = 0,
        onForecastDetails = {}
    )
}

@Preview
@Composable
private fun PreviewShimmerDailyForecastItem() {
    ShimmerDailyForecastItem()
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
