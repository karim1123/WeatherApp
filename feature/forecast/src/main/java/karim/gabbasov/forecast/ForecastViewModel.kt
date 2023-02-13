package karim.gabbasov.forecast

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.common.di.util.AppCoroutineDispatchers
import karim.gabbasov.data.repository.LocationResult
import karim.gabbasov.data.repository.LocationTracker
import karim.gabbasov.data.repository.WeatherApiResult
import karim.gabbasov.data.repository.WeatherRepository
import karim.gabbasov.ui.mapper.EntityToDisplayableWeatherInfo
import karim.gabbasov.ui.model.DisplayableWeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val application: Application,
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val dispatcher: AppCoroutineDispatchers,
    private val mapper: EntityToDisplayableWeatherInfo
) : ViewModel() {

    private val _forecastUiState: MutableStateFlow<ForecastUiState> =
        MutableStateFlow(ForecastUiState.Nothing)
    val forecastUiState = _forecastUiState.asStateFlow()

    init {
        loadWeatherInfo()
    }

    fun loadWeatherInfo() = viewModelScope.launch(dispatcher.io) {
        _forecastUiState.value = ForecastUiState.Loading
        if (isPermissionsGranted()) {
            if (isGpsEnabled()) {
                getCurrentLocation()
            } else {
                _forecastUiState.value = ForecastUiState.Error(ForecastErrors.GpsIsTurnedOff)
            }
        } else {
            _forecastUiState.value = ForecastUiState.Error(ForecastErrors.PermissionNotGranted)
        }
    }

    private suspend fun getCurrentLocation() {
        when (val locationResult = locationTracker.getLocation()) {
            is LocationResult.Success -> {
                getWeather(
                    lat = locationResult.data.latitude,
                    long = locationResult.data.longitude
                )
            }
            LocationResult.UnknownError -> {
                ForecastUiState.Error(ForecastErrors.UnknownLocationError)
            }
        }
    }

    private suspend fun getWeather(lat: Double, long: Double) {
        when (repository.refreshWeather(lat, long)) {
            is WeatherApiResult.Success -> {
                repository.weather.flowOn(dispatcher.io)
                    .collect { data ->
                        _forecastUiState.value = ForecastUiState.Success(
                            weatherInfo = mapper.map(data),
                            error = null
                        )
                    }
            }
            else -> {
                if (repository.isBdEmpty()) _forecastUiState.value =
                    ForecastUiState.Error(error = ForecastErrors.NoNetworkConnection)
                else repository.weather.flowOn(dispatcher.io).collect { data ->
                    _forecastUiState.value =
                        ForecastUiState.Success(
                            weatherInfo = mapper.map(data),
                            error = ForecastErrors.OutdatedData
                        )
                }
            }
        }
    }

    private fun isPermissionsGranted(): Boolean {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return !(!hasAccessCoarseLocationPermission && !hasAccessFineLocationPermission)
    }

    private fun isGpsEnabled(): Boolean {
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}

/**
 * Provides the status of downloading weather forecast data request.
 */
sealed interface ForecastUiState {

    object Nothing : ForecastUiState

    /**
     * Response is a success, containing [weatherInfo].
     */
    data class Success(
        val weatherInfo: DisplayableWeatherInfo,
        val error: ForecastErrors?
    ) : ForecastUiState

    /**
     * Response was a failure, with the [error].
     */
    data class Error(val error: ForecastErrors) : ForecastUiState

    /**
     * Response in progress.
     */
    object Loading : ForecastUiState
}

enum class ForecastErrors {
    PermissionNotGranted,
    GpsIsTurnedOff,
    UnknownLocationError,
    OutdatedData,
    NoNetworkConnection
}
