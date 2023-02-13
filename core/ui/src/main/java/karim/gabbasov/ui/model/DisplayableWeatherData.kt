package karim.gabbasov.ui.model

data class DisplayableWeatherData(
    val hour: Int,
    val temperatureCelsius: Int,
    val feelsLike: Int,
    val pressure: Int,
    val windSpeed: Int,
    val humidity: Int,
    val weatherType: WeatherType
)
