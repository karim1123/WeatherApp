package karim.gabbasov.detailedforecast.mapper

import karim.gabbasov.common.Mapper
import karim.gabbasov.common.util.PartsOfDayRanges
import karim.gabbasov.detailedforecast.model.DisplayableDailyWeatherDataByTimeOfDay
import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherType
import karim.gabbasov.ui.util.DayOfWeekNames.Companion.toDayOfWeekNames
import karim.gabbasov.ui.util.WeatherMapUtils.toMostSevereWeatherCondition
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject
import kotlin.math.roundToInt

internal typealias EntityToDisplayableDailyWeatherInfo = Mapper<
    @JvmSuppressWildcards WeatherInfo,
    @JvmSuppressWildcards ImmutableList<DisplayableDailyWeatherDataByTimeOfDay>
    >

private enum class WeatherParameters {
    TEMPERATURE_PARAMETER,
    FEELS_LIKE_PARAMETER,
    WIND_SPEED_PARAMETER,
    PRESSURE_PARAMETER,
    HUMIDITY_PARAMETER
}

internal class EntityToDisplayableDailyWeatherInfoMapper @Inject constructor(
    private val mapper: WeatherCodeAndDateToWeatherType
) : Mapper<
        @JvmSuppressWildcards WeatherInfo,
        @JvmSuppressWildcards ImmutableList<DisplayableDailyWeatherDataByTimeOfDay>
        > {

    private val timeRanges = listOf(
        PartsOfDayRanges.MORNING.range,
        PartsOfDayRanges.DAY.range,
        PartsOfDayRanges.EVENING.range,
        PartsOfDayRanges.NIGHT.range
    )

    override fun map(
        fromObject: WeatherInfo
    ): ImmutableList<DisplayableDailyWeatherDataByTimeOfDay> {
        val dailyForecast = mutableListOf<DisplayableDailyWeatherDataByTimeOfDay>()
        fromObject.hourlyWeatherData.forEach {
            dailyForecast.add(it.value.toDisplayableDailyWeatherData())
        }
        return dailyForecast.toImmutableList()
    }

    private fun List<WeatherData>
    .toDisplayableDailyWeatherData(): DisplayableDailyWeatherDataByTimeOfDay {
        val weatherType = timeRanges.map { range ->
            this.slice(range).toMostSevereWeatherCondition(mapper)
        }.toImmutableList()
        val tabData = Pair(
            this[0].time.dayOfMonth,
            this[0].time.dayOfWeek.value.toDayOfWeekNames()
        )

        return DisplayableDailyWeatherDataByTimeOfDay(
            temperature = this.averageParameterByTimeOfDay(WeatherParameters.TEMPERATURE_PARAMETER),
            feelsLike = this.averageParameterByTimeOfDay(WeatherParameters.FEELS_LIKE_PARAMETER),
            windSpeed = this.averageParameterByTimeOfDay(WeatherParameters.WIND_SPEED_PARAMETER),
            pressure = this.averageParameterByTimeOfDay(WeatherParameters.PRESSURE_PARAMETER),
            humidity = this.averageParameterByTimeOfDay(WeatherParameters.HUMIDITY_PARAMETER),
            weatherType = weatherType,
            tabData = tabData
        )
    }

    private fun List<WeatherData>.averageParameterByTimeOfDay(
        parameter: WeatherParameters
    ): ImmutableList<Int> {
        return timeRanges.map { range ->
            this.slice(range).map { weatherData ->
                when (parameter) {
                    WeatherParameters.TEMPERATURE_PARAMETER -> weatherData.temperature
                    WeatherParameters.FEELS_LIKE_PARAMETER -> weatherData.feelsLike
                    WeatherParameters.WIND_SPEED_PARAMETER -> weatherData.windSpeed
                    WeatherParameters.PRESSURE_PARAMETER -> weatherData.pressure
                    WeatherParameters.HUMIDITY_PARAMETER -> weatherData.humidity
                }
            }.average().roundToInt()
        }.toImmutableList()
    }
}
