package karim.gabbasov.weatherapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.api.features.DetailedForecastFeatureApi
import karim.gabbasov.api.features.ForecastFeatureApi
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val forecastApi: ForecastFeatureApi,
    val detailedForecastApi: DetailedForecastFeatureApi
) : ViewModel()
