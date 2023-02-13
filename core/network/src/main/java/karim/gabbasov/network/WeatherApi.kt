package karim.gabbasov.network

import com.skydoves.sandwich.ApiResponse
import karim.gabbasov.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(
        "v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m," +
            "windspeed_10m,pressure_msl,apparent_temperature"
    )
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): ApiResponse<WeatherDto>
}
