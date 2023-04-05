package karim.gabbasov.forecast

import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.api.features.DetailedForecastFeatureApi
import karim.gabbasov.common.util.AppCoroutineDispatchers
import karim.gabbasov.common.util.CheckPermissionsUtil
import karim.gabbasov.common.util.NetworkConnectivityUtil
import karim.gabbasov.data.repository.LocationResult
import karim.gabbasov.data.repository.LocationTracker
import karim.gabbasov.data.repository.WeatherApiResult
import karim.gabbasov.data.repository.WeatherRepository
import karim.gabbasov.forecast.mapper.EntityToDisplayableWeatherInfo
import karim.gabbasov.forecast.util.CheckGpsUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("TooManyFunctions")
@HiltViewModel
internal class ForecastViewModel @Inject constructor(
    private val application: Application,
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val dispatcher: AppCoroutineDispatchers,
    private val mapper: EntityToDisplayableWeatherInfo,
    val detailedForecastFeatureApi: DetailedForecastFeatureApi
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState> =
        MutableStateFlow(UIState.getInitialUIState(application))
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    init {
        loadWeatherInfo()
    }

    fun execute(action: ViewActions) {
        when (action) {
            ViewActions.CheckIsGPSEnabled -> {
                checkIsGPSEnabled()
                loadWeatherInfo()
            }
            ViewActions.CheckIsInternetConnectionAvailable -> {
                checkIsInternetConnectionAvailable()
                loadWeatherInfo()
            }
            ViewActions.CheckIsPermissionGranted -> {
                checkIsPermissionGranted()
                loadWeatherInfo()
            }
            ViewActions.GoToAppSettings -> {
                goToAppSettings()
            }
            ViewActions.GetForecast -> {
                loadWeatherInfo()
            }
        }
    }

    private fun checkIsGPSEnabled() {
        state = state.copy(isGPSEnabled = CheckGpsUtil.isGpsEnabled(application))
    }

    private fun checkIsInternetConnectionAvailable() {
        state = state.copy(
            isInternetConnectionAvailable = NetworkConnectivityUtil.isInternetAvailable(
                application
            )
        )
    }

    private fun goToAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", application.packageName, null)
        )
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        application.startActivity(intent)
    }

    private fun checkIsPermissionGranted() {
        state = state.copy(
            isPermissionGranted = CheckPermissionsUtil.isOneOfPermissionsGranted(
                application,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun loadWeatherInfo() = viewModelScope.launch(dispatcher.main) {
        if (
            state.isPermissionGranted && state.isGPSEnabled && state.isInternetConnectionAvailable
        ) {
            state = state.copy(isLoading = true)
            getCurrentLocation()
        } else if (
            state.isPermissionGranted && state.isGPSEnabled && !state.isInternetConnectionAvailable
        ) {
            getWeatherRequestFailed()
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
                state = state.copy(locationRequestFailed = true)
            }
        }
    }

    private suspend fun getWeather(lat: Double, long: Double) {
        when (repository.refreshWeather(lat, long)) {
            is WeatherApiResult.Success -> {
                getWeatherFromBd()
            }
            else -> {
                getWeatherRequestFailed()
            }
        }
    }

    private suspend fun getWeatherRequestFailed() {
        if (repository.isBdEmpty()) {
            state = state.copy(
                networkRequestFailed = true,
                isLoading = false
            )
        } else {
            state = state.copy(isDataOutdated = true)
            getWeatherFromBd()
        }
    }

    private suspend fun getWeatherFromBd() {
        repository.weather.collect { data ->
            state = state.copy(
                weatherInfo = mapper.map(data),
                isLoading = false
            )
        }
    }
}
