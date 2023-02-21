package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface ForecastFeatureApi : FeatureApi {

    fun forecastRoute(): String
}
