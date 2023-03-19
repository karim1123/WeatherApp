package karim.gabbasov.api.features

import karim.gabbasov.api.FeatureApi

interface ForecastFeatureApi : FeatureApi {

    fun forecastRoute(): String
}
