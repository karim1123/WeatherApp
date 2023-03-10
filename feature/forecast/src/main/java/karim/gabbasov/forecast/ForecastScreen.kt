package karim.gabbasov.forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
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
import karim.gabbasov.forecast.uicomponents.LocationPermission
import karim.gabbasov.forecast.uicomponents.WeatherForecastCard
import karim.gabbasov.ui.R
import karim.gabbasov.ui.R.string.snackbar_network_error_content
import karim.gabbasov.ui.R.string.update
import karim.gabbasov.ui.theme.RedDark
import karim.gabbasov.ui.theme.RedLight
import karim.gabbasov.ui.theme.WeatherTheme
import karim.gabbasov.ui.ui.ErrorCard
import karim.gabbasov.ui.ui.ErrorSnackbar

@Composable
fun ForecastScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val weatherState by viewModel.forecastUiState.collectAsStateWithLifecycle()
    WeatherTheme {
        ForecastScreen(
            forecastUiState = weatherState,
            viewModel = viewModel,
            modifier = modifier
        )
    }
}

@Composable
fun ForecastScreen(
    forecastUiState: ForecastUiState,
    viewModel: ForecastViewModel,
    modifier: Modifier = Modifier
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
                        .fillMaxSize()
                ) {
                    WeatherForecastCard(
                        currentWeather = forecastUiState.weatherInfo.currentWeatherData,
                        hourlyWeather = forecastUiState.weatherInfo.hourlyWeatherData,
                        textColor = Color.White,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                }
                if (forecastUiState.error != null) {
                    ErrorSnackbar(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        backGroundColor = Brush.horizontalGradient(listOf(RedLight, RedDark)),
                        textColor = Color.White,
                        onClick = { viewModel.loadWeatherInfo() },
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
                            onClick = { viewModel.loadWeatherInfo() },
                            backGroundColor = Brush.horizontalGradient(listOf(RedLight, RedDark)),
                            title = stringResource(R.string.gps_off_title),
                            errorMessage = stringResource(R.string.gps_error_message),
                            icon = ImageVector.vectorResource(id = R.drawable.ic_location),
                            iconDescription = stringResource(R.string.gps_off_title)
                        )
                    }
                    ForecastErrors.PermissionNotGranted -> {
                        LocationPermission(viewModel)
                    }
                    ForecastErrors.NoNetworkConnection -> {
                        ErrorCard(
                            onClick = { viewModel.loadWeatherInfo() },
                            backGroundColor = Brush.horizontalGradient(listOf(RedLight, RedDark)),
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
