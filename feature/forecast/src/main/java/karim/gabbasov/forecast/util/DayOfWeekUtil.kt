package karim.gabbasov.forecast.util

import karim.gabbasov.ui.util.DayOfWeekNames
import karim.gabbasov.ui.util.DayOfWeekNames.Companion.toDayOfWeekNames
import java.time.LocalDateTime

internal object DayOfWeekUtil {
    internal fun LocalDateTime.toDayOfWeek(currentTime: LocalDateTime): DayOfWeekNames {
        if (this.dayOfMonth == currentTime.dayOfMonth) return DayOfWeekNames.TODAY
        return if (this.dayOfMonth == currentTime.plusDays(1).dayOfMonth) {
            DayOfWeekNames.TOMORROW
        } else {
            this.dayOfWeek.value.toDayOfWeekNames()
        }
    }
}
