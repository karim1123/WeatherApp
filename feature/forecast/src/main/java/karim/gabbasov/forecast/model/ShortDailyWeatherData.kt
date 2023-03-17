package karim.gabbasov.forecast.model

import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.model.WeatherType

internal sealed class ShortDailyWeatherData {
    internal class Weekend(
        val forecast: ShortForecastForDay
    ) : ShortDailyWeatherData()

    internal class CommonDay(
        val forecast: ShortForecastForDay
    ) : ShortDailyWeatherData()
}

internal data class ShortForecastForDay(
    val dayOfMonth: Int,
    val monthName: MonthNames,
    val dayOfWeek: DayOfWeekNames,
    val weatherType: WeatherType,
    val maxTemperature: Int,
    val minTemperature: Int
)
