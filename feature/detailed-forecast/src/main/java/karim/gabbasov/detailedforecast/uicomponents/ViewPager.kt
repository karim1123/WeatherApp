package karim.gabbasov.detailedforecast.uicomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.detailedforecast.model.DisplayableDailyWeatherDataByTimeOfDay
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.WeatherCondition
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val ALL_PAGES = 7

/**
 * Для увеличения скорости загрузки экрана
 * изначально отрисовывается только одна страница View Pager-а,
 * после загрузки значение beyondBoundsPageCount меняется на 7
 * для избежания лагов при быстром скроле страниц.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ViewPager(
    detailedForecast: ImmutableList<DisplayableDailyWeatherDataByTimeOfDay>,
    pagerState: PagerState
) {
    HorizontalPager(
        pageCount = detailedForecast.size,
        state = pagerState,
        beyondBoundsPageCount = ALL_PAGES
    ) { page ->
        Column(modifier = Modifier.fillMaxSize()) {
            DailyTemperature(dailyForecast = detailedForecast[page])
            Spacer(modifier = Modifier.height(4.dp))
            DailyWindSpeed(detailedForecast[page].windSpeed)
            Spacer(modifier = Modifier.height(4.dp))
            DailyHumidity(detailedForecast[page].humidity)
            Spacer(modifier = Modifier.height(4.dp))
            DailyPressure(detailedForecast[page].pressure)
        }
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewViewPager() {
    val weatherType = persistentListOf(
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
    )
    val weatherData = persistentListOf(
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(12, DayOfWeekNames.MONDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(13, DayOfWeekNames.TUESDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(14, DayOfWeekNames.WEDNESDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(15, DayOfWeekNames.THURSDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(16, DayOfWeekNames.FRIDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(17, DayOfWeekNames.SATURDAY)
        ),
        DisplayableDailyWeatherDataByTimeOfDay(
            temperature = persistentListOf(3, 10, 5, 2),
            feelsLike = persistentListOf(1, 8, 3, -1),
            windSpeed = persistentListOf(8, 8, 7, 6),
            pressure = persistentListOf(1031, 1035, 1038, 1026),
            humidity = persistentListOf(84, 80, 73, 91),
            weatherType = weatherType,
            tabData = Pair(18, DayOfWeekNames.SUNDAY)
        )
    )
    ViewPager(
        detailedForecast = weatherData,
        pagerState = rememberPagerState(3)
    )
}
