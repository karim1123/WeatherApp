package karim.gabbasov.ui.mapper

import karim.gabbasov.common.Mapper
import karim.gabbasov.common.util.PartsOfDayRanges
import karim.gabbasov.ui.R
import karim.gabbasov.ui.model.WeatherType
import karim.gabbasov.ui.util.WeatherCondition
import java.time.LocalDateTime
import javax.inject.Inject

typealias WeatherCodeAndDateToWeatherType =
    Mapper<@JvmSuppressWildcards Pair<Int, LocalDateTime>, WeatherType>

@Suppress("LongMethod", "MagicNumber", "CyclomaticComplexMethod")
internal class WeatherCodeAndDateToWeatherTypeMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards Pair<Int, LocalDateTime>, WeatherType> {

    private val nightRange = PartsOfDayRanges.NIGHT.range

    override fun map(fromObject: Pair<Int, LocalDateTime>): WeatherType {
        return when (weatherCodeToWeatherCondition(fromObject.first)) {
            WeatherCondition.CLEAR_SKY -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_night
                    )
                }
            }
            WeatherCondition.MAINLY_CLEAR -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_night
                    )
                }
            }
            WeatherCondition.PARTLY_CLOUDY -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.cloudy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.cloudy_3_night
                    )
                }
            }
            WeatherCondition.OVERCAST -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.cloudy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.cloudy_3_night
                    )
                }
            }
            WeatherCondition.FOG -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.fog_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.fog_night
                    )
                }
            }
            WeatherCondition.DEPOSITING_RIME_FOG -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.fog_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.fog_night
                    )
                }
            }
            WeatherCondition.DRIZZLE -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_1_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_1_night
                    )
                }
            }
            WeatherCondition.SLIGHT_INTENSITY_RAIN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_1_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_1_night
                    )
                }
            }
            WeatherCondition.MODERATE_INTENSITY_RAIN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_2_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_2_night
                    )
                }
            }
            WeatherCondition.HEAVY_INTENSITY_RAIN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_3_night
                    )
                }
            }
            WeatherCondition.LIGHT_INTENSITY_FREEZING_RAIN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snow_and_sleet_mix
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snow_and_sleet_mix
                    )
                }
            }
            WeatherCondition.HEAVY_INTENSITY_FREEZING_RAIN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snow_and_sleet_mix
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snow_and_sleet_mix
                    )
                }
            }
            WeatherCondition.SLIGHT_INTENSITY_SNOW -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_1_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_1_night
                    )
                }
            }
            WeatherCondition.MODERATE_INTENSITY_SNOW -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_2_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_2_night
                    )
                }
            }
            WeatherCondition.HEAVY_INTENSITY_SNOW -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_3_night
                    )
                }
            }
            WeatherCondition.SNOW_GRAINS -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_1_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_1_night
                    )
                }
            }
            WeatherCondition.RAIN_SHOWERS -> {
                if (fromObject.second.hour in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.rainy_3_night
                    )
                }
            }
            WeatherCondition.SNOW_SHOWERS -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_3_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.snowy_3_night
                    )
                }
            }
            WeatherCondition.SLIGHT_THUNDERSTORM -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.scattered_thunderstorms_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.scattered_thunderstorms_night
                    )
                }
            }
            WeatherCondition.THUNDERSTORM_WITH_HAIL -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.scattered_thunderstorms_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.scattered_thunderstorms_night
                    )
                }
            }
            WeatherCondition.UNKNOWN -> {
                if (fromObject.second.hour !in nightRange) {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_day
                    )
                } else {
                    WeatherType(
                        weatherCondition = weatherCodeToWeatherCondition(fromObject.first),
                        iconRes = R.drawable.clear_night
                    )
                }
            }
        }
    }

    private fun weatherCodeToWeatherCondition(weatherCode: Int): WeatherCondition {
        return when (weatherCode) {
            0 -> WeatherCondition.CLEAR_SKY
            1 -> WeatherCondition.MAINLY_CLEAR
            2 -> WeatherCondition.PARTLY_CLOUDY
            3 -> WeatherCondition.OVERCAST
            45 -> WeatherCondition.FOG
            48 -> WeatherCondition.DEPOSITING_RIME_FOG
            51 -> WeatherCondition.DRIZZLE
            53 -> WeatherCondition.DRIZZLE
            55 -> WeatherCondition.DRIZZLE
            56 -> WeatherCondition.DRIZZLE
            57 -> WeatherCondition.DRIZZLE
            61 -> WeatherCondition.SLIGHT_INTENSITY_RAIN
            63 -> WeatherCondition.MODERATE_INTENSITY_RAIN
            65 -> WeatherCondition.HEAVY_INTENSITY_RAIN
            66 -> WeatherCondition.LIGHT_INTENSITY_FREEZING_RAIN
            67 -> WeatherCondition.HEAVY_INTENSITY_FREEZING_RAIN
            71 -> WeatherCondition.SLIGHT_INTENSITY_SNOW
            73 -> WeatherCondition.MODERATE_INTENSITY_SNOW
            75 -> WeatherCondition.HEAVY_INTENSITY_SNOW
            77 -> WeatherCondition.SNOW_GRAINS
            80 -> WeatherCondition.RAIN_SHOWERS
            81 -> WeatherCondition.RAIN_SHOWERS
            82 -> WeatherCondition.RAIN_SHOWERS
            85 -> WeatherCondition.SNOW_SHOWERS
            86 -> WeatherCondition.SNOW_SHOWERS
            95 -> WeatherCondition.SLIGHT_THUNDERSTORM
            96 -> WeatherCondition.THUNDERSTORM_WITH_HAIL
            99 -> WeatherCondition.THUNDERSTORM_WITH_HAIL
            else -> WeatherCondition.UNKNOWN
        }
    }
}
