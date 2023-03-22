package karim.gabbasov.designsystem.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Suppress("ConstructorParameterNaming")
data class WeatherAppShapes(
    val CurrentWeatherCard: CornerBasedShape = RoundedCornerShape(
        bottomStart = 30.dp,
        bottomEnd = 30.dp
    )
)

val LocalShapes = staticCompositionLocalOf { WeatherAppShapes() }
