package karim.gabbasov.data.repository

interface LocationRepository {
    suspend fun refreshAddressByCoordinates(lat: Double, long: Double): LocationApiResult
}
