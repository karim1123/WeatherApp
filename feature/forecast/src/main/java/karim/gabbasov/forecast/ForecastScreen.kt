package karim.gabbasov.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import karim.gabbasov.designsystem.theme.DarkRed
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.forecast.uicomponents.CurrentWeatherForecastCard
import karim.gabbasov.forecast.uicomponents.LocationPermission
import karim.gabbasov.forecast.uicomponents.ShortDailyForecast
import karim.gabbasov.ui.R
import karim.gabbasov.ui.R.string.snackbar_network_error_content
import karim.gabbasov.ui.R.string.update
import karim.gabbasov.ui.ui.ErrorCard
import karim.gabbasov.ui.ui.ErrorSnackbar

@Composable
internal fun ForecastScreenRoute(
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val weatherState by viewModel.forecastUiState.collectAsStateWithLifecycle()
    ForecastScreen(
        forecastUiState = weatherState,
        loadForecastInfo = { viewModel.loadWeatherInfo() },
        onForecastDetails = {
            navController.navigate(
                viewModel.detailedForecastFeatureApi.detailedForecastRoute(it)
            ) {
                launchSingleTop = true
            }
        }
    )
}

@Suppress("LongMethod")
@Composable
private fun ForecastScreen(
    forecastUiState: ForecastUiState,
    modifier: Modifier = Modifier,
    loadForecastInfo: () -> Unit,
    onForecastDetails: (Int) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (forecastUiState) {
            is ForecastUiState.Loading -> CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
            is ForecastUiState.Success -> {
                Column(
                    modifier = modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(WeatherAppTheme.colors.background)
                        .verticalScroll(rememberScrollState())
                ) {
                    CurrentWeatherForecastCard(
                        currentWeather = forecastUiState.weatherInfo.currentWeatherData,
                        hourlyWeather = forecastUiState.weatherInfo.hourlyWeatherData,
                        textColor = White,
                        secondTextColor = LightGrey
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    ShortDailyForecast(
                        dailyData = forecastUiState.weatherInfo.shorDailyWeatherData,
                        onForecastDetails = onForecastDetails
                    )
                }
                if (forecastUiState.error != null) {
                    ErrorSnackbar(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        backGroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
                        textColor = Color.White,
                        onClick = loadForecastInfo,
                        snackbarState = true,
                        buttonText = update,
                        messageText = snackbar_network_error_content
                    )
                }
            }
            is ForecastUiState.Error -> {
                when (forecastUiState.error) {
                    ForecastErrors.GpsIsTurnedOff -> {
                        ErrorCard(
                            onClick = loadForecastInfo,
                            backGroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
                            title = stringResource(R.string.gps_off_title),
                            errorMessage = stringResource(R.string.gps_error_message),
                            icon = ImageVector.vectorResource(id = R.drawable.ic_location),
                            iconDescription = stringResource(R.string.gps_off_title)
                        )
                    }
                    ForecastErrors.PermissionNotGranted -> {
                        LocationPermission(loadForecastInfo)
                    }
                    ForecastErrors.NoNetworkConnection -> {
                        ErrorCard(
                            onClick = loadForecastInfo,
                            backGroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
                            title = stringResource(R.string.no_network_error_title),
                            errorMessage = stringResource(R.string.no_network_error_subtitle),
                            icon = ImageVector.vectorResource(id = R.drawable.ic_mobile_tower),
                            iconDescription = stringResource(R.string.no_network_error_title)
                        )
                    }
                    else -> {}
                }
            }
            is ForecastUiState.Nothing -> {}
        }
    }
}
