package karim.gabbasov.ui.model

data class DisplayableWeatherInfo(
    val currentWeatherData: DisplayableWeatherData,
    val hourlyWeatherData: List<HourlyWeatherData>
)
