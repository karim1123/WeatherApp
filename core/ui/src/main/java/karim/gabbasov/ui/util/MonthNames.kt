package karim.gabbasov.ui.util

import karim.gabbasov.ui.R

enum class MonthNames {
    JANUARY {
        override val monthNameId = R.string.january
        override val shortMonthNameId = R.string.january_short
    },
    FEBRUARY {
        override val monthNameId = R.string.february
        override val shortMonthNameId = R.string.february_short
    },
    MARCH {
        override val monthNameId = R.string.march
        override val shortMonthNameId = R.string.march_short
    },
    APRIL {
        override val monthNameId = R.string.april
        override val shortMonthNameId = R.string.april_short
    },
    MAY {
        override val monthNameId = R.string.may
        override val shortMonthNameId = R.string.may_short
    },
    JUNE {
        override val monthNameId = R.string.june
        override val shortMonthNameId = R.string.june_short
    },
    JULY {
        override val monthNameId = R.string.july
        override val shortMonthNameId = R.string.july_short
    },
    AUGUST {
        override val monthNameId = R.string.august
        override val shortMonthNameId = R.string.august_short
    },
    SEPTEMBER {
        override val monthNameId = R.string.september
        override val shortMonthNameId = R.string.september_short
    },
    OCTOBER {
        override val monthNameId = R.string.october
        override val shortMonthNameId = R.string.october_short
    },
    NOVEMBER {
        override val monthNameId = R.string.november
        override val shortMonthNameId = R.string.november_short
    },
    DECEMBER {
        override val monthNameId = R.string.december
        override val shortMonthNameId = R.string.december_short
    },
    UNKNOWN {
        override val monthNameId = R.string.unknown
        override val shortMonthNameId = R.string.unknown
    };

    abstract val monthNameId: Int
    abstract val shortMonthNameId: Int

    companion object {
        fun Int.toMonthName(): MonthNames {
            return when (this) {
                1 -> JANUARY
                2 -> FEBRUARY
                3 -> MARCH
                4 -> APRIL
                5 -> MAY
                6 -> JUNE
                7 -> JULY
                8 -> AUGUST
                9 -> SEPTEMBER
                10 -> OCTOBER
                11 -> NOVEMBER
                12 -> DECEMBER
                else -> {
                    UNKNOWN
                }
            }
        }
    }
}
