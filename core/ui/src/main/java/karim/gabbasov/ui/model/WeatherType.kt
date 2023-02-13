package karim.gabbasov.ui.model

import androidx.annotation.DrawableRes

data class WeatherType(
    val weatherCondition: WeatherCondition,
    @DrawableRes val iconRes: Int
)
