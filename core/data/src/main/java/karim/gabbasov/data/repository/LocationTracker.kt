package karim.gabbasov.data.repository

interface LocationTracker {
    suspend fun getLocation(): LocationResult
}
