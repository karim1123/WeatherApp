package karim.gabbasov.data.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import karim.gabbasov.common.util.AppCoroutineDispatchers
import karim.gabbasov.data.BuildConfig
import karim.gabbasov.network.LocationApi
import karim.gabbasov.network.model.location.LocationSuggestionsDto
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocationRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi,
    private val dispatcher: AppCoroutineDispatchers
) : LocationRepository {

    override suspend fun refreshAddressByCoordinates(lat: Double, long: Double): LocationApiResult =
        withContext(dispatcher.io) {
            val response = locationApi.getAddressByCoordinates(
                token = BuildConfig.DADATA_API_KEY,
                lat = lat,
                long = long
            )
            response.suspendOnSuccess {
                TODO("Add response to bd")
            }
            return@withContext handleResponse(response)
        }

    private fun handleResponse(response: ApiResponse<LocationSuggestionsDto>): LocationApiResult {
        return when (response) {
            is ApiResponse.Success -> LocationApiResult.Success
            is ApiResponse.Failure.Error -> LocationApiResult.Error
            is ApiResponse.Failure.Exception -> LocationApiResult.Exception
        }
    }
}

sealed class LocationApiResult {
    object Success : LocationApiResult()
    object Error : LocationApiResult()
    object Exception : LocationApiResult()
}
