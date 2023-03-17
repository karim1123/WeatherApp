package karim.gabbasov.detailed_forecast

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.detailed_forecast.model.DisplayableDailyWeatherDataByTimeOfDay
import karim.gabbasov.detailed_forecast.uicomponents.TabLayout
import karim.gabbasov.detailed_forecast.uicomponents.TopBar
import karim.gabbasov.detailed_forecast.uicomponents.ViewPager
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.WeatherCondition
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun DetailedForecastScreenRoute(
    navController: NavController,
    viewModel: DetailedForecastViewModel = hiltViewModel(),
    chosenPage: Int
) {
    val detailedForecast by viewModel.detailedForecastInfo.collectAsStateWithLifecycle()
    DetailedForecastScreen(
        chosenPage = chosenPage,
        detailedForecast = detailedForecast,
        onBack = { navController.popBackStack() },
        drawPager = { viewModel.drawPager() }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun DetailedForecastScreen(
    chosenPage: Int,
    detailedForecast: UIState,
    onBack: () -> Unit,
    drawPager: () -> Unit
) {
    if (detailedForecast.forecastData != null) {
        val tabData = detailedForecast.forecastData.map { it.tabData }
        val pagerState = rememberPagerState(initialPage = chosenPage)
        var isInit by rememberSaveable { mutableStateOf(true) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(onBack = onBack) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(WeatherAppTheme.colors.forecastDetailsBackground)
            ) {
                TabLayout(
                    tabsData = tabData,
                    pagerState = pagerState
                )
                if (detailedForecast.drawPager) {
                    ViewPager(
                        detailedForecast = detailedForecast.forecastData,
                        pagerState = pagerState,
                        isInit = isInit,
                        onIsInitChange = { isInit = false }
                    )
                } else {
                    drawPager.invoke()
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDetailedForecastScreen() {
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
    DetailedForecastScreen(
        chosenPage = 3,
        detailedForecast = UIState(
            forecastData = weatherData,
            drawPager = true
        ),
        onBack = {},
        drawPager = {}
    )
}
