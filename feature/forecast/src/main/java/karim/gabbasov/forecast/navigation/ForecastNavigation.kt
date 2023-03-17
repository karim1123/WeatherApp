package karim.gabbasov.forecast.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.features.ForecastFeatureApi
import karim.gabbasov.forecast.ForecastScreenRoute
import javax.inject.Inject

private const val ROUTE = "forecast"

class ForecastNavigation @Inject constructor() : ForecastFeatureApi {

    override fun forecastRoute() = ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(ROUTE) {
            ForecastScreenRoute(
                navController = navController
            )
        }
    }
}
