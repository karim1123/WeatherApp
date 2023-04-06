package karim.gabbasov.forecast

import android.Manifest
import android.app.Application
import karim.gabbasov.common.util.CheckPermissionsUtil
import karim.gabbasov.common.util.NetworkConnectivityUtil
import karim.gabbasov.forecast.model.DisplayableWeatherInfo
import karim.gabbasov.forecast.util.CheckAutoTImeUtil
import karim.gabbasov.forecast.util.CheckGpsUtil
import karim.gabbasov.forecast.util.GetInitialShortDailyWeatherDataUtil

internal data class UIState(
    val weatherInfo: DisplayableWeatherInfo?,
    val isPermissionGranted: Boolean,
    val isGPSEnabled: Boolean,
    val isInternetConnectionAvailable: Boolean,
    val isAutoTimeEnable: Boolean,
    val isDataOutdated: Boolean,
    val isLoading: Boolean,
    val networkRequestFailed: Boolean,
    val locationRequestFailed: Boolean,
    val showNowInternetConnectionError: Boolean
) {
    internal companion object {
        internal fun getInitialUIState(app: Application): UIState {
            return UIState(
                weatherInfo = if (CheckAutoTImeUtil.isTimeAutomatic(app)) {
                    DisplayableWeatherInfo(
                        currentWeatherData = null,
                        hourlyWeatherData = null,
                        shorDailyWeatherData =
                        GetInitialShortDailyWeatherDataUtil.getShortDailyWeatherData()
                    )
                } else {
                    null
                },
                isPermissionGranted = CheckPermissionsUtil.isOneOfPermissionsGranted(
                    app,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                isGPSEnabled = CheckGpsUtil.isGpsEnabled(app),
                isInternetConnectionAvailable = NetworkConnectivityUtil.isInternetAvailable(app),
                isAutoTimeEnable = CheckAutoTImeUtil.isTimeAutomatic(app),
                isDataOutdated = false,
                isLoading = false,
                networkRequestFailed = false,
                locationRequestFailed = false,
                showNowInternetConnectionError = false
            )
        }
    }
}
