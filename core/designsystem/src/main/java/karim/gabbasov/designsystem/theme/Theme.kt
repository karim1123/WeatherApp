package karim.gabbasov.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

fun lightColors() = WeatherAppColors(
    background = LightBackground,
    currentWeatherCard = LightCurrentWeatherCard,
    dailyForecastCard = White,
    dailyForecastText = Black,
    dailyForecastSecondText = LightGrey,
    requestPermissionCard = White,
    requestPermissionText = Black,
    forecastDetailsBackground = ForecastDetailsLightBackground,
    isLight = true
)

fun darkColors() = WeatherAppColors(
    background = DarkBackground,
    currentWeatherCard = DarkCurrentWeatherCard,
    dailyForecastCard = DarkShortDailyForecastCard,
    dailyForecastText = White,
    dailyForecastSecondText = DarkGrey,
    requestPermissionCard = ErrorDarkColor,
    requestPermissionText = White,
    forecastDetailsBackground = Black,
    isLight = false
)

@Composable
fun WeatherAppTheme(
    typography: OnlineShopTypography = WeatherAppTheme.typography,
    colors: WeatherAppColors = WeatherAppTheme.colors,
    darkColors: WeatherAppColors = darkColors(),
    shapes: WeatherAppShapes = WeatherAppTheme.shapes,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColor = remember { if (darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalShapes provides shapes,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(
            typography.value.copy(color = WeatherAppTheme.colors.background), content = content
        )
    }
}
