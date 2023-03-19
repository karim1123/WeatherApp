package karim.gabbasov.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object WeatherAppTheme {

    val colors: WeatherAppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: WeatherAppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: WeatherAppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}
