package karim.gabbasov.forecast.model

import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.MonthNames

internal sealed class ShortDailyWeatherData {
    internal class Weekend(
        val forecast: ShortForecastForDay?,
        val dateData: DateData
    ) : ShortDailyWeatherData()

    internal class CommonDay(
        val forecast: ShortForecastForDay?,
        val dateData: DateData
    ) : ShortDailyWeatherData()
}

internal data class ShortForecastForDay(
    val weatherType: WeatherType,
    val maxTemperature: Int,
    val minTemperature: Int
)

internal data class DateData(
    val dayOfMonth: Int,
    val monthName: MonthNames,
    val dayOfWeek: DayOfWeekNames
)
