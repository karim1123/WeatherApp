package karim.gabbasov.ui.util

import karim.gabbasov.ui.R
import javax.annotation.concurrent.Immutable

@Immutable
enum class DayOfWeekNames {
    MONDAY {
        override val dayOfWeekNameId = R.string.monday
        override val shortDayOfWeekNameId = R.string.monday_short
    },
    TUESDAY {
        override val dayOfWeekNameId = R.string.tuesday
        override val shortDayOfWeekNameId = R.string.tuesday_short
    },
    WEDNESDAY {
        override val dayOfWeekNameId = R.string.wednesday
        override val shortDayOfWeekNameId = R.string.wednesday_short
    },
    THURSDAY {
        override val dayOfWeekNameId = R.string.thursday
        override val shortDayOfWeekNameId = R.string.thursday_short
    },
    FRIDAY {
        override val dayOfWeekNameId = R.string.friday
        override val shortDayOfWeekNameId = R.string.friday_short
    },
    SATURDAY {
        override val dayOfWeekNameId = R.string.saturday
        override val shortDayOfWeekNameId = R.string.saturday_short
    },
    SUNDAY {
        override val dayOfWeekNameId = R.string.sunday
        override val shortDayOfWeekNameId = R.string.sunday_short
    },
    TODAY {
        override val dayOfWeekNameId = R.string.today
        override val shortDayOfWeekNameId = R.string.today
    },
    TOMORROW {
        override val dayOfWeekNameId = R.string.tomorrow
        override val shortDayOfWeekNameId = R.string.tomorrow
    },
    UNKNOWN {
        override val dayOfWeekNameId = R.string.unknown
        override val shortDayOfWeekNameId = R.string.unknown
    };

    abstract val dayOfWeekNameId: Int
    abstract val shortDayOfWeekNameId: Int

    companion object {
        fun Int.toDayOfWeekNames(): DayOfWeekNames {
            return when (this) {
                1 -> MONDAY
                2 -> TUESDAY
                3 -> WEDNESDAY
                4 -> THURSDAY
                5 -> FRIDAY
                6 -> SATURDAY
                7 -> SUNDAY
                else -> UNKNOWN
            }
        }
    }
}
