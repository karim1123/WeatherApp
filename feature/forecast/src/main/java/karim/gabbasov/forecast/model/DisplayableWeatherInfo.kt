package karim.gabbasov.forecast.model

internal data class DisplayableWeatherInfo(
    val currentWeatherData: DisplayableWeatherData?,
    val hourlyWeatherData: List<HourlyWeatherData>?,
    val shorDailyWeatherData: List<ShortDailyWeatherData>
)
