package karim.gabbasov.data.repository

import karim.gabbasov.model.data.weather.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    val weather: Flow<WeatherInfo>

    suspend fun refreshWeather(lat: Double, long: Double): WeatherApiResult

    suspend fun isBdEmpty(): Boolean
}
