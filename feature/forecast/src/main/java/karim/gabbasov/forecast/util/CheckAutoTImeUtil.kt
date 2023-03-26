package karim.gabbasov.forecast.util

import android.content.Context
import android.provider.Settings

private const val AUTO_TIME_ENABLE = 1

internal object CheckAutoTImeUtil {

    internal fun isTimeAutomatic(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AUTO_TIME,
            0
        ) == AUTO_TIME_ENABLE
    }
}
