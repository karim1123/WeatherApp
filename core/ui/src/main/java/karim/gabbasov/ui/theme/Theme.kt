package karim.gabbasov.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorPalette = lightColorScheme(
    primary = White,
    primaryContainer = White,
    background = White,
    secondary = LightBlue,
    surface = Black
)

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimary,
    primaryContainer = DarkPrimaryVariant,
    background = Black,
    secondary = DarkBlue,
    surface = White
)

/** Light Android background theme */
private val LightAndroidBackgroundTheme = BackgroundTheme(color = White)

/** Dark Android background theme */
private val DarkAndroidBackgroundTheme = BackgroundTheme(color = Black)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette
    val backgroundTheme = if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
