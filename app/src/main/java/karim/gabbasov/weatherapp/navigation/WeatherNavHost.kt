package karim.gabbasov.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import karim.gabbasov.api.register
import karim.gabbasov.weatherapp.presentation.MainActivityViewModel

@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = viewModel.forecastApi.forecastRoute()
    ) {
        register(
            viewModel.forecastApi,
            navController = navController,
            modifier = modifier
        )
        register(
            viewModel.detailedForecastApi,
            navController = navController,
            modifier = modifier
        )
    }
}
