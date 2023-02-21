package karim.gabbasov.data

import karim.gabbasov.data.mapper.WeatherEntityMappers
import karim.gabbasov.database.WeatherForecastEntity
import karim.gabbasov.network.model.WeatherDataDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class WeatherEntityMappersTest {

    private lateinit var map: WeatherEntityMappers

    @Before
    fun setup() {
        map = WeatherEntityMappers()
    }

    @Test
    fun `toWeatherForecastEntity converts WeatherDataDto to WeatherForecastEntity`() {
        val delta = 0.0
        val input = WeatherDataDto(
            time = listOf("2023-02-15T09:00:00", "2023-02-15T09:00:00", "2023-02-15T09:00:00"),
            temperatures = listOf(13.0, 14.0, 15.0),
            weatherCodes = listOf(1, 1, 1),
            pressures = listOf(1012.0, 1020.0, 1019.0),
            windSpeeds = listOf(8.0, 8.0, 8.6),
            humidities = listOf(80.0, 79.0, 79.8),
            feelsLike = listOf(11.0, 12.0, 13.0)
        )

        val output = map.toWeatherForecastEntity(input)

        assertEquals(input.time.first(), output.first().time)
        assertEquals(input.temperatures.first(), output.first().temperature, delta)
        assertEquals(input.weatherCodes.first(), output.first().weatherCode)
        assertEquals(input.pressures.first(), output.first().pressure, delta)
        assertEquals(input.windSpeeds.first(), output.first().windSpeed, delta)
        assertEquals(input.humidities.first(), output.first().humidity, delta)
        assertEquals(input.feelsLike.first(), output.first().feelsLikeTemperature, delta)
    }

    @Test
    fun `toWeatherInfo converts WeatherForecastEntity to WeatherInfo `() {
        val input = WeatherForecastEntity(
            id = 0,
            time = "2023-02-14T00:00",
            temperature = 14.0,
            weatherCode = 1,
            pressure = 1019.0,
            windSpeed = 8.0,
            humidity = 80.0,
            feelsLikeTemperature = 10.0
        )

        val output = map.toWeatherInfo(listOf(input))

        assertEquals(
            LocalDateTime.parse(input.time, DateTimeFormatter.ISO_DATE_TIME),
            output.hourlyWeatherData.values.first().first().time
        )
        assertEquals(
            input.temperature.roundToInt(),
            output.hourlyWeatherData.values.first().first().temperature
        )
        assertEquals(input.weatherCode, output.hourlyWeatherData.values.first().first().weatherCode)
        assertEquals(
            input.pressure.roundToInt(),
            output.hourlyWeatherData.values.first().first().pressure
        )
        assertEquals(
            input.windSpeed.roundToInt(),
            output.hourlyWeatherData.values.first().first().windSpeed
        )
        assertEquals(
            input.humidity.roundToInt(),
            output.hourlyWeatherData.values.first().first().humidity
        )
        assertEquals(
            input.feelsLikeTemperature.roundToInt(),
            output.hourlyWeatherData.values.first().first().feelsLike
        )
    }
}
