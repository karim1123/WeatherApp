package karim.gabbasov.data.repository

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import karim.gabbasov.model.data.Coordinates
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient
) : LocationTracker {

    override suspend fun getLocation(): LocationResult {
        val lastKnownLocation = getLastKnownLocation()
        return if (lastKnownLocation != null) {
            LocationResult.Success(lastKnownLocation)
        } else {
            LocationResult.UnknownError
        }
    }

    private suspend fun getLastKnownLocation(): Coordinates? {
        return suspendCancellableCoroutine { cont ->
            lastLocation { lastLocation ->
                if (lastLocation != null) {
                    cont.resume(Coordinates(lastLocation.latitude, lastLocation.longitude))
                } else {
                    cont.resume(null)
                }
            }
        }
    }

    private fun lastLocation(complete: (Location?) -> Unit) {
        try {
            locationClient.lastLocation.addOnCompleteListener { lastLocationTask ->
                when {
                    !lastLocationTask.isSuccessful -> complete(null)
                    lastLocationTask.result == null -> {
                        getCurrentLocation(complete, Priority.PRIORITY_HIGH_ACCURACY)
                    }
                    else -> complete(lastLocationTask.result)
                }
            }
        } catch (e: SecurityException) {
            return complete(null)
        }
    }

    private fun getCurrentLocation(complete: (Location?) -> Unit, priority: Int) {
        try {
            locationClient.getCurrentLocation(priority, null)
                .addOnCompleteListener { currentLocationTask ->
                    if (!currentLocationTask.isSuccessful) {
                        complete(null)
                    } else {
                        complete(currentLocationTask.result)
                    }
                }
        } catch (e: SecurityException) {
            return complete(null)
        }
    }
}

sealed class LocationResult {
    data class Success(val data: Coordinates) : LocationResult()

    object UnknownError : LocationResult()
}
