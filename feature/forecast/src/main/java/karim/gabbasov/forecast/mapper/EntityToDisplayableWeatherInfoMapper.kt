package karim.gabbasov.forecast.mapper

import karim.gabbasov.common.Mapper
import karim.gabbasov.forecast.model.DateData
import karim.gabbasov.forecast.model.DisplayableWeatherData
import karim.gabbasov.forecast.model.DisplayableWeatherInfo
import karim.gabbasov.forecast.model.HourlyWeatherData
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.forecast.model.ShortForecastForDay
import karim.gabbasov.forecast.util.DayOfWeekUtil.toDayOfWeek
import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherType
import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.DayOfWeekNames.Companion.toDayOfWeekNames
import karim.gabbasov.ui.util.MonthNames.Companion.toMonthName
import karim.gabbasov.ui.util.WeatherMapUtils.toMostSevereWeatherCondition
import karim.gabbasov.ui.util.WeatherMapUtils.toWeatherType
import java.time.LocalDateTime
import javax.inject.Inject

private const val FIRST_HOUR_OF_NEW_DAY = 0

internal typealias EntityToDisplayableWeatherInfo =
    Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo>

internal class EntityToDisplayableWeatherInfoMapper @Inject constructor(
    private val mapper: WeatherCodeAndDateToWeatherType
) : Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo> {

    override fun map(fromObject: WeatherInfo): DisplayableWeatherInfo {
        val currentHour = LocalDateTime.now().hour
        val currentWeather = fromObject.hourlyWeatherData[0]!!.toCurrentWeather(currentHour)
        val hourlyForecast = fromObject.toHourlyWeatherData(currentHour)
        val dailyForecast = fromObject.toShortDailyWeatherData()
        return DisplayableWeatherInfo(
            currentWeatherData = currentWeather,
            hourlyWeatherData = hourlyForecast,
            shorDailyWeatherData = dailyForecast
        )
    }

    private fun WeatherInfo.toHourlyWeatherData(currentHour: Int): List<HourlyWeatherData> {
        val hourlyForecast = mutableListOf<HourlyWeatherData>()
        this.hourlyWeatherData[0]?.filter { it.time.hour >= currentHour }
            ?.forEach { weatherData ->
                when (weatherData.time.hour) {
                    currentHour -> {
                        hourlyForecast.add(
                            index = 0,
                            HourlyWeatherData.ElementsOfWeather(
                                windSpeed = weatherData.windSpeed,
                                pressure = weatherData.pressure,
                                humidity = weatherData.humidity
                            )
                        )
                        hourlyForecast.add(
                            HourlyWeatherData.CurrentHour(
                                temperature = weatherData.temperature,
                                weatherType = weatherData.toWeatherType(mapper)
                            )
                        )
                    }
                    else -> hourlyForecast.add(
                        HourlyWeatherData.DefaultHour(
                            hour = weatherData.time.hour,
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType(mapper)
                        )
                    )
                }
            }

        val lastIndex = hourlyForecast.lastIndex
        this.hourlyWeatherData[1]?.filter { it.time.hour <= currentHour }
            ?.forEach { weatherData ->
                val tomorrowDate =
                    this.hourlyWeatherData.firstNotNullOf { it.value.first().time.plusDays(1) }
                when (weatherData.time.hour) {
                    FIRST_HOUR_OF_NEW_DAY -> hourlyForecast.add(
                        index = lastIndex + 1,
                        HourlyWeatherData.FirstHourOfNewDay(
                            dayOfMonth = tomorrowDate.dayOfMonth,
                            monthName = tomorrowDate.month.value.toMonthName(),
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType(mapper)
                        )
                    )
                    else -> hourlyForecast.add(
                        HourlyWeatherData.DefaultHour(
                            hour = weatherData.time.hour,
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType(mapper)
                        )
                    )
                }
            }
        return hourlyForecast
    }

    private fun WeatherInfo.toShortDailyWeatherData(): List<ShortDailyWeatherData> {
        val dailyForecast = mutableListOf<ShortDailyWeatherData>()
        val currentDate = this.hourlyWeatherData.firstNotNullOf { it.value.first().time }
        for (day in this.hourlyWeatherData) {
            val dayOfWeek = day.value[0].time.dayOfWeek.value.toDayOfWeekNames()
            if (dayOfWeek == DayOfWeekNames.SATURDAY || dayOfWeek == DayOfWeekNames.SUNDAY) {
                dailyForecast.add(
                    ShortDailyWeatherData.Weekend(
                        forecast = day.toShortForecastForDay(),
                        dateData = day.toShortDateData(currentDate)
                    )
                )
            } else {
                dailyForecast.add(
                    ShortDailyWeatherData.CommonDay(
                        forecast = day.toShortForecastForDay(),
                        dateData = day.toShortDateData(currentDate)
                    )
                )
            }
        }
        return dailyForecast
    }

    private fun Map.Entry<Int, List<WeatherData>>.toShortDateData(
        currentTime: LocalDateTime
    ): DateData {
        return DateData(
            dayOfMonth = this.value[0].time.dayOfMonth,
            monthName = this.value[0].time.monthValue.toMonthName(),
            dayOfWeek = this.value[0].time.toDayOfWeek(currentTime)
        )
    }

    private fun Map.Entry<Int, List<WeatherData>>.toShortForecastForDay(): ShortForecastForDay {
        return ShortForecastForDay(
            weatherType = this.value.toMostSevereWeatherCondition(mapper),
            maxTemperature = this.value.maxOf { it.temperature },
            minTemperature = this.value.minOf { it.temperature }
        )
    }

    private fun List<WeatherData>.toCurrentWeather(currentHour: Int): DisplayableWeatherData {
        val current = this.first { it.time.hour == currentHour }
        return DisplayableWeatherData(
            hour = current.time.hour,
            temperatureCelsius = current.temperature,
            feelsLike = current.feelsLike,
            pressure = current.pressure,
            windSpeed = current.windSpeed,
            humidity = current.humidity,
            weatherType = current.toWeatherType(mapper)
        )
    }
}
