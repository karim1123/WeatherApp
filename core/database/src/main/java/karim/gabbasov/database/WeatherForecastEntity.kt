package karim.gabbasov.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherForecastEntity(
    @PrimaryKey
    val id: Int,
    val time: String,
    val temperature: Double,
    val weatherCode: Int,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val feelsLikeTemperature: Double
)
