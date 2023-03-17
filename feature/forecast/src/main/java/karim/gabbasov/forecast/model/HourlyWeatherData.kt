package karim.gabbasov.forecast.model

import karim.gabbasov.ui.util.MonthNames
import karim.gabbasov.ui.model.WeatherType

internal sealed interface HourlyWeatherData {
    data class ElementsOfWeather(
        val windSpeed: Int,
        val pressure: Int,
        val humidity: Int,
    ) : HourlyWeatherData

    data class CurrentHour(
        val temperature: Int,
        val weatherType: WeatherType
    ) : HourlyWeatherData

    data class FirstHourOfNewDay(
        val dayOfMonth: Int,
        val monthName: MonthNames,
        val temperature: Int,
        val weatherType: WeatherType
    ) : HourlyWeatherData

    data class DefaultHour(
        val hour: Int,
        val temperature: Int,
        val weatherType: WeatherType
    ) : HourlyWeatherData
}
