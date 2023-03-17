package karim.gabbasov.data.mapper

import karim.gabbasov.database.WeatherForecastEntity
import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.network.model.WeatherDataDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.roundToInt

private const val HOURS_IN_DAY = 24

internal class WeatherEntityMappers @Inject constructor() {

    fun toWeatherForecastEntity(dto: WeatherDataDto): List<WeatherForecastEntity> {
        dto.apply {
            return time.mapIndexed { index, time ->
                val temperature = temperatures[index]
                val weatherCode = weatherCodes[index]
                val windSpeed = windSpeeds[index]
                val pressure = pressures[index]
                val humidity = humidities[index]
                val feelsLike = feelsLike[index]
                WeatherForecastEntity(
                    id = index,
                    time = time,
                    temperature = temperature,
                    weatherCode = weatherCode,
                    pressure = pressure,
                    windSpeed = windSpeed,
                    humidity = humidity,
                    feelsLikeTemperature = feelsLike
                )
            }
        }
    }

    private data class IndexedWeatherData(
        val index: Int,
        val data: WeatherData
    )

    private fun toWeatherDataMap(entity: List<WeatherForecastEntity>): Map<Int, List<WeatherData>> {
        return entity.mapIndexed { index, weather ->
            IndexedWeatherData(
                index = index,
                data = WeatherData(
                    time = LocalDateTime.parse(weather.time, DateTimeFormatter.ISO_DATE_TIME),
                    temperature = weather.temperature.roundToInt(),
                    feelsLike = weather.feelsLikeTemperature.roundToInt(),
                    pressure = weather.pressure.roundToInt(),
                    windSpeed = weather.windSpeed.roundToInt(),
                    humidity = weather.humidity.roundToInt(),
                    weatherCode = weather.weatherCode
                )
            )
        }.groupBy {
            it.index / HOURS_IN_DAY
        }.mapValues { it ->
            it.value.map { it.data }
        }
    }

    fun toWeatherInfo(entity: List<WeatherForecastEntity>): WeatherInfo {
        val weatherDataMap = toWeatherDataMap(entity)

        return WeatherInfo(
            hourlyWeatherData = weatherDataMap
        )
    }
}
