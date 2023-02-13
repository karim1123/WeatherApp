package karim.gabbasov.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WeatherForecastDaoTest {

    private lateinit var db: WeatherForecastDatabase
    private lateinit var weatherDao: WeatherForecastDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            context = InstrumentationRegistry.getInstrumentation().context,
            klass = WeatherForecastDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
            .also {
                weatherDao = it.weatherData()
            }
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun noWeatherForecastReturnsNull() = runBlocking {
        assertTrue(weatherDao.count() == 0)
    }

    @Test
    fun insertWithListOfItemsAddsNewItems() = runBlocking {
        weatherDao.insertWeatherData(listOf(createWeatherForecastEntity()))
        assertTrue(weatherDao.count() != 0)
    }

    @Test
    fun insertModelReplacesMatchingItem() = runBlocking {
        weatherDao.insertWeatherData(
            listOf(
                createWeatherForecastEntity(id = 0),
                createWeatherForecastEntity(id = 1),
                createWeatherForecastEntity(id = 3)
            )
        )
        assertTrue(weatherDao.count() == 3)
        weatherDao.insertWeatherData(
            listOf(
                createWeatherForecastEntity(id = 0),
                createWeatherForecastEntity(id = 1),
                createWeatherForecastEntity(id = 3)
            )
        )
        assertTrue(weatherDao.count() == 3)
    }

    @Test
    fun insertModelEqualsToGetModel() = runBlocking {
        weatherDao.insertWeatherData(
            listOf(
                createWeatherForecastEntity(id = 0),
                createWeatherForecastEntity(id = 1),
                createWeatherForecastEntity(id = 3)
            )
        )
        val weatherData = weatherDao.getWeatherData().first()
        assertEquals(
            listOf(
                createWeatherForecastEntity(id = 0),
                createWeatherForecastEntity(id = 1),
                createWeatherForecastEntity(id = 3)
            ),
            weatherData
        )
    }

    private fun createWeatherForecastEntity(
        id: Int = 0,
        time: String = "2023-02-14T00:00",
        temperature: Double = 14.0,
        weatherCode: Int = 0,
        pressure: Double = 1016.0,
        windSpeed: Double = 5.0,
        humidity: Double = 80.0,
        feelsLikeTemperature: Double = 12.0
    ) = WeatherForecastEntity(
        id = id,
        time = time,
        temperature = temperature,
        weatherCode = weatherCode,
        pressure = pressure,
        windSpeed = windSpeed,
        humidity = humidity,
        feelsLikeTemperature = feelsLikeTemperature
    )
}
