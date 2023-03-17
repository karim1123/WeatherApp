package karim.gabbasov.ui.model

import androidx.annotation.DrawableRes
import karim.gabbasov.ui.util.WeatherCondition
import javax.annotation.concurrent.Immutable

@Immutable
data class WeatherType(
    val weatherCondition: WeatherCondition,
    @DrawableRes val iconRes: Int
)
