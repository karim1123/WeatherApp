package karim.gabbasov.model.data.weather

data class WeatherInfo(
    val hourlyWeatherData: Map<Int, List<WeatherData>>,
)
