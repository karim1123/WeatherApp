package karim.gabbasov.network

import com.skydoves.sandwich.ApiResponse
import karim.gabbasov.network.model.location.LocationSuggestionsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LocationApi {

    @GET("suggestions/api/4_1/rs/geolocate/address?language=ru&count=1")
    suspend fun getAddressByCoordinates(
        @Header("Authorization") token: String,
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
    ): ApiResponse<LocationSuggestionsDto>
}
