package karim.gabbasov.designsystem.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class WeatherAppColors(
    background: Color,
    currentWeatherCard: Color,
    dailyForecastCard: Color,
    dailyForecastText: Color,
    dailyForecastSecondText: Color,
    requestPermissionCard: Color,
    requestPermissionText: Color,
    forecastDetailsBackground: Color,
    isLight: Boolean,
) {
    var background by mutableStateOf(background)
        private set

    var currentWeatherCard by mutableStateOf(currentWeatherCard)
        private set

    var dailyForecastCard by mutableStateOf(dailyForecastCard)
        private set

    var dailyForecastText by mutableStateOf(dailyForecastText)
        private set

    var dailyForecastSecondText by mutableStateOf(dailyForecastSecondText)
        private set

    var requestPermissionCard by mutableStateOf(requestPermissionCard)
        private set

    var requestPermissionText by mutableStateOf(requestPermissionText)
        private set

    var forecastDetailsBackground by mutableStateOf(forecastDetailsBackground)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun copy(
        background: Color = this.background,
        currentWeatherCard: Color = this.currentWeatherCard,
        dailyForecastCard: Color = this.dailyForecastCard,
        dailyForecastText: Color = this.dailyForecastText,
        dailyForecastSecondText: Color = this.dailyForecastSecondText,
        requestPermissionCard: Color = this.requestPermissionCard,
        requestPermissionText: Color = this.requestPermissionText,
        forecastDetailsBackground: Color = this.forecastDetailsBackground,
        isLight: Boolean = this.isLight
    ) = WeatherAppColors(
        background = background,
        currentWeatherCard = currentWeatherCard,
        dailyForecastCard = dailyForecastCard,
        dailyForecastText = dailyForecastText,
        dailyForecastSecondText = dailyForecastSecondText,
        requestPermissionCard = requestPermissionCard,
        requestPermissionText = requestPermissionText,
        forecastDetailsBackground = forecastDetailsBackground,
        isLight = isLight
    )

    fun updateColorsFrom(other: WeatherAppColors) {
        background = other.background
        currentWeatherCard = other.currentWeatherCard
        dailyForecastCard = other.dailyForecastCard
        dailyForecastText = other.dailyForecastText
        dailyForecastSecondText = other.dailyForecastSecondText
        requestPermissionCard = other.requestPermissionCard
        requestPermissionText = other.requestPermissionText
        forecastDetailsBackground = other.forecastDetailsBackground
        isLight = other.isLight
    }
}

val LocalColors = staticCompositionLocalOf { lightColors() }
