package karim.gabbasov.detailed_forecast.util

import karim.gabbasov.ui.R

internal enum class PartsOfDay(val partOfDayResId: Int) {
    MORNING(R.string.morning_title),
    DAY(R.string.day_title),
    EVENING(R.string.evening_title),
    NIGHT(R.string.night_title),
    UNKNOWN(R.string.unknown);
    companion object {
        fun getOfPartOfDayResIdByIndex(index: Int): Int {
            return when (index) {
                0 -> MORNING.partOfDayResId
                1 -> DAY.partOfDayResId
                2 -> EVENING.partOfDayResId
                3 -> NIGHT.partOfDayResId
                else -> UNKNOWN.partOfDayResId
            }
        }
    }
}
