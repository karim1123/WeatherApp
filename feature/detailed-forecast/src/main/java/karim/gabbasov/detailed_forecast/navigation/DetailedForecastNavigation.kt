package karim.gabbasov.detailed_forecast.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import karim.gabbasov.detailed_forecast.DetailedForecastScreenRoute
import karim.gabbasov.feature_api.features.DetailedForecastFeatureApi
import javax.inject.Inject

private const val ROUTE = "detailedForecast"
private const val PARAMETER_KEY = "page"

class DetailedForecastNavigation @Inject constructor() : DetailedForecastFeatureApi {

    override fun detailedForecastRoute(page: Int) = "$ROUTE/$page"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = "$ROUTE/{$PARAMETER_KEY}",
            arguments = listOf(navArgument(PARAMETER_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            DetailedForecastScreenRoute(
                navController = navController,
                chosenPage = backStackEntry.arguments?.getInt(PARAMETER_KEY) ?: 0
            )
        }
    }
}
