package karim.gabbasov.data.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import karim.gabbasov.common.util.AppCoroutineDispatchers
import karim.gabbasov.data.mapper.WeatherEntityMappers
import karim.gabbasov.database.WeatherForecastDao
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.network.WeatherApi
import karim.gabbasov.network.model.weather.WeatherDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherForecastDao,
    private val mapper: WeatherEntityMappers,
    private val dispatchers: AppCoroutineDispatchers
) : WeatherRepository {

    override val weather: Flow<WeatherInfo>
        get() = weatherDao.getWeatherData()
            .map { mapper.toWeatherInfo(it) }
            .flowOn(dispatchers.io)

    override suspend fun refreshWeather(
        lat: Double,
        long: Double,
    ): WeatherApiResult = withContext(dispatchers.io) {
        val response = weatherApi.getWeatherData(lat, long)
        response.suspendOnSuccess {
            weatherDao.insertWeatherData(
                mapper.toWeatherForecastEntity(data.weatherData)
            )
        }
        return@withContext handleResponse(response)
    }

    override suspend fun isBdEmpty() = withContext(dispatchers.io) {
        weatherDao.count() == 0
    }

    private fun handleResponse(response: ApiResponse<WeatherDto>): WeatherApiResult {
        return when (response) {
            is ApiResponse.Success -> WeatherApiResult.Success
            is ApiResponse.Failure.Error -> WeatherApiResult.Error
            is ApiResponse.Failure.Exception -> WeatherApiResult.Exception
        }
    }
}

sealed class WeatherApiResult {
    object Success : WeatherApiResult()
    object Error : WeatherApiResult()
    object Exception : WeatherApiResult()
}
