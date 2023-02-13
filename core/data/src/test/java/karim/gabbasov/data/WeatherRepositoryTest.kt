package karim.gabbasov.data

import com.skydoves.sandwich.ApiResponse
import io.mockk.mockk
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.Runs
import karim.gabbasov.data.mapper.WeatherEntityMappers
import karim.gabbasov.data.repository.WeatherApiResult
import karim.gabbasov.data.repository.WeatherRepositoryImpl
import karim.gabbasov.database.WeatherForecastDao
import karim.gabbasov.network.WeatherApi
import karim.gabbasov.network.model.WeatherDataDto
import karim.gabbasov.network.model.WeatherDto
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class WeatherRepositoryTest {

    private val weatherApi = mockk<WeatherApi>()
    private val weatherDao = mockk<WeatherForecastDao>()
    private val mapper = mockk<WeatherEntityMappers>()
    private val repository = WeatherRepositoryImpl(weatherApi, weatherDao, mapper)

    @Test
    fun `refreshWeather should insert data into weatherDao when API response is successful`() =
        runBlocking {
            val lat = 42.0
            val long = 73.0
            val weatherDataDto = mockk<WeatherDataDto>()
            val weatherDto = WeatherDto(weatherDataDto)
            val apiResponse = ApiResponse.Success(Response.success(weatherDto))
            coEvery { weatherApi.getWeatherData(lat, long) } returns apiResponse
            coEvery { mapper.toWeatherForecastEntity(weatherDataDto) } returns emptyList()
            coEvery { weatherDao.insertWeatherData(this.any()) } just Runs

            val result = repository.refreshWeather(lat, long)

            coVerify(exactly = 1) { weatherDao.insertWeatherData(any()) }
            assertEquals(WeatherApiResult.Success, result)
        }

    @Test
    fun `refreshWeather should return Error when API response is a Failure Error`() = runBlocking {
        val lat = 42.0
        val long = 73.0
        val responseBody = mockk<ResponseBody>()
        val mediaType = mockk<MediaType>()
        coEvery { responseBody.contentType() } returns mediaType
        coEvery { responseBody.contentLength() } returns 1
        val response = Response.error<WeatherDto>(404, responseBody)
        val apiResponse = ApiResponse.Failure.Error(response)

        coEvery { weatherApi.getWeatherData(lat, long) } returns apiResponse

        val result = repository.refreshWeather(lat, long)

        assertEquals(WeatherApiResult.Error, result)
    }

    @Test
    fun `refreshWeather should return Exception when API response is a Failure Exception`() =
        runBlocking {
            val lat = 42.0
            val long = 73.0
            val apiResponse = ApiResponse.Failure.Exception<WeatherDto>(Exception())
            coEvery { weatherApi.getWeatherData(lat, long) } returns apiResponse

            val result = repository.refreshWeather(lat, long)

            assertEquals(WeatherApiResult.Exception, result)
        }
}
