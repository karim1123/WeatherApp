package karim.gabbasov.common.util

enum class PartsOfDayRanges(val range: IntRange) {
    MORNING(6..11),
    DAY(12..17),
    EVENING(18..23),
    NIGHT(0..5)
}
