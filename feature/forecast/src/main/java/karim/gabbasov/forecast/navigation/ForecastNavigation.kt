package karim.gabbasov.forecast.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.features.ForecastFeatureApi
import karim.gabbasov.forecast.ForecastScreenRoute

class ForecastNavigation : ForecastFeatureApi {

    private val route = "forecast"

    override fun forecastRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            ForecastScreenRoute(modifier = modifier)
        }
    }
}
