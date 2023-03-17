package karim.gabbasov.forecast.model

import karim.gabbasov.ui.model.WeatherType

internal data class DisplayableWeatherData(
    val hour: Int,
    val temperatureCelsius: Int,
    val feelsLike: Int,
    val pressure: Int,
    val windSpeed: Int,
    val humidity: Int,
    val weatherType: WeatherType
)
