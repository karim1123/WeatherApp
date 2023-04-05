package karim.gabbasov.data.repository

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import karim.gabbasov.common.util.AppCoroutineDispatchers
import karim.gabbasov.model.data.Coordinates
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

internal class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val dispatcher: AppCoroutineDispatchers
) : LocationTracker {

    override suspend fun getLocation(): LocationResult = withContext(dispatcher.io) {
        val lastKnownLocation = getLastKnownLocation()
        return@withContext if (lastKnownLocation != null) {
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
                        getCurrentLocation(complete)
                    }
                    else -> complete(lastLocationTask.result)
                }
            }
        } catch (e: SecurityException) {
            Log.d("exception", e.message.toString())
            return complete(null)
        }
    }

    private fun getCurrentLocation(complete: (Location?) -> Unit) {
        try {
            locationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnCompleteListener { currentLocationTask ->
                    if (!currentLocationTask.isSuccessful) {
                        complete(null)
                    } else {
                        complete(currentLocationTask.result)
                    }
                }
        } catch (e: SecurityException) {
            Log.d("exception", e.message.toString())
            return complete(null)
        }
    }
}

sealed class LocationResult {
    data class Success(val data: Coordinates) : LocationResult()

    object UnknownError : LocationResult()
}
