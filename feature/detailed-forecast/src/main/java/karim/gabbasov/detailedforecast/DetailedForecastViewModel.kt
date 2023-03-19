package karim.gabbasov.detailedforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.common.util.AppCoroutineDispatchers
import karim.gabbasov.data.repository.WeatherRepository
import karim.gabbasov.detailedforecast.mapper.EntityToDisplayableDailyWeatherInfo
import karim.gabbasov.detailedforecast.model.DisplayableDailyWeatherDataByTimeOfDay
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailedForecastViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatcher: AppCoroutineDispatchers,
    private val mapper: EntityToDisplayableDailyWeatherInfo,
) : ViewModel() {

    private val _detailedForecastInfo: MutableStateFlow<UIState> = MutableStateFlow(
        UIState(forecastData = null, drawPager = false)
    )
    val detailedForecastInfo = _detailedForecastInfo.asStateFlow()

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.weather.flowOn(dispatcher.io).collect { data ->
            _detailedForecastInfo.update {
                UIState(
                    forecastData = mapper.map(data),
                    drawPager = false
                )
            }
        }
    }

    @Suppress("MagicNumber")
    fun drawPager() = viewModelScope.launch {
        if (detailedForecastInfo.value.forecastData != null) {
            delay(200)
            _detailedForecastInfo.update { _detailedForecastInfo.value.copy(drawPager = true) }
        }
    }
}

internal data class UIState(
    val forecastData: ImmutableList<DisplayableDailyWeatherDataByTimeOfDay>?,
    val drawPager: Boolean
)