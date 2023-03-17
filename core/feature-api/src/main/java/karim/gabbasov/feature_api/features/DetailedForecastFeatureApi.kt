package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface DetailedForecastFeatureApi : FeatureApi {

    fun detailedForecastRoute(page: Int): String
}
