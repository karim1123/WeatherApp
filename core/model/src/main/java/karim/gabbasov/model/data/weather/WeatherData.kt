package karim.gabbasov.model.data.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperature: Int,
    val feelsLike: Int,
    val pressure: Int,
    val windSpeed: Int,
    val humidity: Int,
    val weatherCode: Int,
)
