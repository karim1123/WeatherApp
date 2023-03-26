package karim.gabbasov.forecast.util

import karim.gabbasov.forecast.model.DateData
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.forecast.util.DayOfWeekUtil.toDayOfWeek
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.DayOfWeekNames.Companion.toDayOfWeekNames
import karim.gabbasov.ui.util.MonthNames.Companion.toMonthName
import java.time.LocalDateTime

private const val DAYS_COUNT = 7

internal object GetInitialShortDailyWeatherDataUtil {

    internal fun getShortDailyWeatherData(): MutableList<ShortDailyWeatherData> {
        val shortDailyWeatherData = mutableListOf<ShortDailyWeatherData>()
        var dayCounter = LocalDateTime.now()
        val currentDate = LocalDateTime.now()

        repeat(DAYS_COUNT) {
            val dateData = DateData(
                dayOfMonth = dayCounter.dayOfMonth,
                monthName = dayCounter.monthValue.toMonthName(),
                dayOfWeek = dayCounter.toDayOfWeek(currentDate)
            )
            if (
                dayCounter.dayOfWeek.value.toDayOfWeekNames() == DayOfWeekNames.SATURDAY ||
                dayCounter.dayOfWeek.value.toDayOfWeekNames() == DayOfWeekNames.SUNDAY
            ) {
                shortDailyWeatherData.add(
                    ShortDailyWeatherData.Weekend(
                        forecast = null,
                        dateData = dateData
                    )
                )
            } else {
                shortDailyWeatherData.add(
                    ShortDailyWeatherData.CommonDay(
                        forecast = null,
                        dateData = dateData
                    )
                )
            }
            dayCounter = dayCounter.plusDays(1)
        }
        return shortDailyWeatherData
    }
}
