package karim.gabbasov.common.util

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object CheckPermissionsUtil {

    fun isOneOfPermissionsGranted(
        application: Application,
        vararg permissions: String
    ): Boolean {
        for (permission in permissions) {
            val isGranted = ContextCompat.checkSelfPermission(
                application,
                permission
            ) == PackageManager.PERMISSION_GRANTED
            if (isGranted) return true
        }
        return false
    }
}
