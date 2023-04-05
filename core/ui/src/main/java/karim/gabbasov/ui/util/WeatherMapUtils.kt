package karim.gabbasov.ui.util

import karim.gabbasov.common.util.PartsOfDayRanges
import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherType
import karim.gabbasov.ui.model.WeatherType

object WeatherMapUtils {

    /**
     * Get the most severe weather condition on a given day
     */
    fun List<WeatherData>.toMostSevereWeatherCondition(
        mapper: WeatherCodeAndDateToWeatherType,
        ignoreNight: Boolean
    ): WeatherType {
        return if (ignoreNight) {
            val mostSevereWeatherData = this
                .drop(PartsOfDayRanges.NIGHT.range.last + 1)
                .maxByOrNull { it.weatherCode } ?: this[0]
            mostSevereWeatherData.toWeatherType(mapper)
        } else {
            val mostSevereWeatherData = this.maxByOrNull { it.weatherCode } ?: this[0]
            mostSevereWeatherData.toWeatherType(mapper)
        }
    }

    fun WeatherData.toWeatherType(mapper: WeatherCodeAndDateToWeatherType): WeatherType {
        return mapper.map(Pair(this.weatherCode, this.time))
    }
}
