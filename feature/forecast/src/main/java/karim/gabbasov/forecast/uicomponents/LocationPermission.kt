package karim.gabbasov.forecast.uicomponents

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import karim.gabbasov.ui.R.string.location_rationale
import karim.gabbasov.ui.ui.CustomPermissionDialog

const val MINIMUM_REQUIRED_NUMBER_OF_PERMISSION = 1

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun LocationPermission(
    loadForecastInfo: () -> Unit
) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    val openDialogCustom = remember { mutableStateOf(true) }

    if (
        locationPermissionsState.allPermissionsGranted ||
        locationPermissionsState.revokedPermissions.size == MINIMUM_REQUIRED_NUMBER_OF_PERMISSION
    ) {
        loadForecastInfo.invoke()
    } else {
        Column {
            if (openDialogCustom.value) {
                CustomPermissionDialog(
                    openDialogCustom = openDialogCustom,
                    locationPermissionsState = locationPermissionsState,
                    rationalBody = stringResource(location_rationale)
                )
            }
        }
    }
}
