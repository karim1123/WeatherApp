package karim.gabbasov.ui.util

import karim.gabbasov.model.data.weather.WeatherData
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherType
import karim.gabbasov.ui.model.WeatherType

object WeatherMapUtils {

    /**
     * Get the most severe weather condition on a given day
     */
    fun List<WeatherData>.toMostSevereWeatherCondition(
        mapper: WeatherCodeAndDateToWeatherType
    ): WeatherType {
        val mostSevereWeatherData = this.maxByOrNull { it.weatherCode } ?: this[0]
        return mostSevereWeatherData.toWeatherType(mapper)
    }

    fun WeatherData.toWeatherType(mapper: WeatherCodeAndDateToWeatherType): WeatherType {
        return mapper.map(Pair(this.weatherCode, this.time))
    }
}
