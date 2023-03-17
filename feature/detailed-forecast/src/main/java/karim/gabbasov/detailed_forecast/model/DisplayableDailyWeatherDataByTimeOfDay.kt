package karim.gabbasov.detailed_forecast.model

import androidx.compose.runtime.Immutable
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.model.WeatherType
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal data class DisplayableDailyWeatherDataByTimeOfDay(
    val temperature: ImmutableList<Int>,
    val feelsLike: ImmutableList<Int>,
    val windSpeed: ImmutableList<Int>,
    val pressure: ImmutableList<Int>,
    val humidity: ImmutableList<Int>,
    val weatherType: ImmutableList<WeatherType>,
    val tabData: Pair<Int, DayOfWeekNames>
)
