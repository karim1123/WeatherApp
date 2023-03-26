package karim.gabbasov.forecast.util

import android.app.Application
import android.content.Context
import android.location.LocationManager

internal object CheckGpsUtil {

    internal fun isGpsEnabled(application: Application): Boolean {
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}
