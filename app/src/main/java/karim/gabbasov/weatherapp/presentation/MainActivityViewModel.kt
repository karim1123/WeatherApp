package karim.gabbasov.weatherapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.feature_api.features.DetailedForecastFeatureApi
import karim.gabbasov.feature_api.features.ForecastFeatureApi
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val forecastApi: ForecastFeatureApi,
    val detailedForecastApi: DetailedForecastFeatureApi
) : ViewModel()
