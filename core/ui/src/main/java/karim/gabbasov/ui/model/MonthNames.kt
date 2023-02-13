package karim.gabbasov.ui.model

import karim.gabbasov.ui.R

enum class MonthNames {
    JANUARY {
        override val stringResId = R.string.january
    },
    FEBRUARY {
        override val stringResId = R.string.february
    },
    MARCH {
        override val stringResId = R.string.march
    },
    APRIL {
        override val stringResId = R.string.april
    },
    MAY {
        override val stringResId = R.string.may
    },
    JUNE {
        override val stringResId = R.string.june
    },
    JULY {
        override val stringResId = R.string.july
    },
    AUGUST {
        override val stringResId = R.string.august
    },
    SEPTEMBER {
        override val stringResId = R.string.september
    },
    OCTOBER {
        override val stringResId = R.string.october
    },
    NOVEMBER {
        override val stringResId = R.string.november
    },
    DECEMBER {
        override val stringResId = R.string.december
    },
    UNKNOWN {
        override val stringResId = R.string.unknown
    };

    abstract val stringResId: Int

    companion object {
        fun Int.toShortMonthName(): MonthNames {
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
