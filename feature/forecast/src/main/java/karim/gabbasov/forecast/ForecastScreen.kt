package karim.gabbasov.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.forecast.model.DisplayableWeatherData
import karim.gabbasov.forecast.model.HourlyWeatherData
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.forecast.uicomponents.CurrentWeatherForecastCard
import karim.gabbasov.forecast.uicomponents.ErrorCard
import karim.gabbasov.forecast.uicomponents.ErrorSnackbar
import karim.gabbasov.forecast.uicomponents.LocationPermission
import karim.gabbasov.forecast.uicomponents.ShortDailyForecast
import karim.gabbasov.ui.R

@Composable
internal fun ForecastScreenRoute(
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val weatherState by viewModel.uiState.collectAsStateWithLifecycle()
    ForecastScreen(
        uiState = weatherState,
        onCheckGPSStatus = { viewModel.execute(ViewActions.CheckIsGPSEnabled) },
        onCheckIsInternetConnectionStatus = {
            viewModel.execute(ViewActions.CheckIsInternetConnectionAvailable)
        },
        onForecastDetails = {
            navController.navigate(
                viewModel.detailedForecastFeatureApi.detailedForecastRoute(it)
            ) {
                launchSingleTop = true
            }
        },
        onChangePermissionState = { viewModel.execute(ViewActions.CheckIsPermissionGranted) },
        onGoToAppSettingsClick = { viewModel.execute(ViewActions.GoToAppSettings) },
        onGetForecast = { viewModel.execute(ViewActions.GetForecast) }
    )
}

@Suppress("LongParameterList")
@Composable
private fun ForecastScreen(
    uiState: UIState,
    modifier: Modifier = Modifier,
    onCheckGPSStatus: () -> Unit,
    onCheckIsInternetConnectionStatus: () -> Unit,
    onForecastDetails: (Int) -> Unit,
    onChangePermissionState: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    onGetForecast: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            uiState.isLoading || uiState.weatherInfo?.hourlyWeatherData != null -> {
                ForecastScreenContent(
                    currentWeather = uiState.weatherInfo?.currentWeatherData,
                    hourlyWeather = uiState.weatherInfo?.hourlyWeatherData,
                    isLoading = uiState.isLoading,
                    dailyData = uiState.weatherInfo?.shorDailyWeatherData,
                    onForecastDetails = onForecastDetails
                )
                ErrorHandler(
                    uiState = uiState,
                    onGetForecast = onGetForecast,
                    onCheckIsInternetConnectionStatus
                )
            }
            !uiState.isPermissionGranted -> {
                LocationPermission(
                    dailyData = uiState.weatherInfo?.shorDailyWeatherData,
                    onChangePermissionState = onChangePermissionState,
                    onGoToAppSettingsClick = onGoToAppSettingsClick
                )
            }
            !uiState.isGPSEnabled -> {
                ErrorCard(
                    onClick = onCheckGPSStatus,
                    title = stringResource(R.string.gps_off_title),
                    errorMessage = stringResource(R.string.gps_error_message),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_location),
                    iconDescription = stringResource(R.string.gps_off_title)
                )
            }
            !uiState.isInternetConnectionAvailable && uiState.networkRequestFailed -> {
                ErrorCard(
                    onClick = onCheckIsInternetConnectionStatus,
                    title = stringResource(R.string.no_network_error_title),
                    errorMessage = stringResource(R.string.no_network_error_subtitle),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_mobile_tower),
                    iconDescription = stringResource(R.string.no_network_error_title)
                )
            }
        }
    }
}

@Composable
internal fun ForecastScreenContent(
    currentWeather: DisplayableWeatherData?,
    hourlyWeather: List<HourlyWeatherData>?,
    isLoading: Boolean,
    dailyData: List<ShortDailyWeatherData>?,
    onForecastDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(WeatherAppTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        CurrentWeatherForecastCard(
            currentWeather = currentWeather,
            hourlyWeather = hourlyWeather,
            isLoading = isLoading,
            textColor = White,
            secondTextColor = LightGrey
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShortDailyForecast(
            dailyData = dailyData,
            isLoading = isLoading,
            onForecastDetails = onForecastDetails
        )
    }
}

@Composable
private fun BoxScope.ErrorHandler(
    uiState: UIState,
    onGetForecast: () -> Unit,
    onCheckIsInternetConnectionStatus: () -> Unit
) {
    when {
        uiState.isDataOutdated -> {
            ErrorSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = onCheckIsInternetConnectionStatus,
                buttonText = R.string.update,
                messageText = R.string.snackbar_network_error_content
            )
        }
        uiState.networkRequestFailed -> {
            ErrorSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = onGetForecast,
                buttonText = R.string.update,
                messageText = R.string.snackbar_network_request_error_content
            )
        }
        uiState.locationRequestFailed -> {
            ErrorSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = onGetForecast,
                buttonText = R.string.update,
                messageText = R.string.snackbar_location_request_error_content
            )
        }
    }
}
