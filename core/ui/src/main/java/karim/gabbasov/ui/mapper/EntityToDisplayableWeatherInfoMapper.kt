package karim.gabbasov.ui.mapper

import karim.gabbasov.common.Mapper
import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.ui.model.DisplayableWeatherData
import karim.gabbasov.ui.model.DisplayableWeatherInfo
import karim.gabbasov.ui.model.HourlyWeatherData
import karim.gabbasov.ui.model.MonthNames.Companion.toShortMonthName
import karim.gabbasov.ui.model.WeatherType
import java.time.LocalDateTime
import javax.inject.Inject

const val FIRST_HOUR_OF_NEW_DAY = 0

typealias EntityToDisplayableWeatherInfo =
    Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo>

internal class EntityToDisplayableWeatherInfoMapper @Inject constructor(
    private val mapper: WeatherCodeAndDateToWeatherType
) : Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo> {

    override fun map(fromObject: WeatherInfo): DisplayableWeatherInfo {
        val hourlyForecast = mutableListOf<HourlyWeatherData>()
        var currentWeather: DisplayableWeatherData? = null
        val currentHour = LocalDateTime.now().hour

        fromObject.hourlyWeatherData[0]?.filter { it.time.hour >= currentHour }
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
                        currentWeather = weatherData.toDisplayableWeatherData()
                        hourlyForecast.add(
                            HourlyWeatherData.CurrentHour(
                                temperature = weatherData.temperature,
                                weatherType = weatherData.toWeatherType()
                            )
                        )
                    }
                    else -> hourlyForecast.add(
                        HourlyWeatherData.DefaultHour(
                            hour = weatherData.time.hour,
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType()
                        )
                    )
                }
            }

        val lastIndex = hourlyForecast.lastIndex
        fromObject.hourlyWeatherData[1]?.filter { it.time.hour <= currentHour }
            ?.forEach { weatherData ->
                val tomorrowDate = LocalDateTime.now().plusDays(1)
                when (weatherData.time.hour) {
                    FIRST_HOUR_OF_NEW_DAY -> hourlyForecast.add(
                        index = lastIndex + 1,
                        HourlyWeatherData.FirstHourOfNewDay(
                            dayOfMonth = tomorrowDate.dayOfMonth,
                            monthName = tomorrowDate.month.value.toShortMonthName(),
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType()
                        )
                    )
                    else -> hourlyForecast.add(
                        HourlyWeatherData.DefaultHour(
                            hour = weatherData.time.hour,
                            temperature = weatherData.temperature,
                            weatherType = weatherData.toWeatherType()
                        )
                    )
                }
            }
        return DisplayableWeatherInfo(
            currentWeatherData = currentWeather!!,
            hourlyWeatherData = hourlyForecast,
        )
    }

    private fun WeatherData.toDisplayableWeatherData(): DisplayableWeatherData {
        return DisplayableWeatherData(
            hour = this.time.hour,
            temperatureCelsius = this.temperature,
            feelsLike = this.feelsLike,
            pressure = this.pressure,
            windSpeed = this.windSpeed,
            humidity = this.humidity,
            weatherType = this.toWeatherType()
        )
    }

    private fun WeatherData.toWeatherType(): WeatherType {
        return mapper.map(Pair(this.weatherCode, this.time))
    }
}
