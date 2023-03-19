package karim.gabbasov.api.features

import karim.gabbasov.api.FeatureApi

interface DetailedForecastFeatureApi : FeatureApi {

    fun detailedForecastRoute(page: Int): String
}
