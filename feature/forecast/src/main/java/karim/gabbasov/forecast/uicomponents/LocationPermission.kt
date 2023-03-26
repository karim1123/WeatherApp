package karim.gabbasov.forecast.uicomponents

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import karim.gabbasov.designsystem.theme.DarkRed
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.forecast.ForecastScreenContent
import karim.gabbasov.forecast.model.ShortDailyWeatherData
import karim.gabbasov.ui.R
import karim.gabbasov.ui.ui.CustomDialog

/**
 * Для получения данных о погоде достаточно только ACCESS_COARSE_LOCATION.
 */
private const val REQUIRED_NUMBER_OF_PERMISSION = 1

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun LocationPermission(
    dailyData: List<ShortDailyWeatherData>?,
    onChangePermissionState: () -> Unit,
    onGoToAppSettingsClick: () -> Unit
) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    val isDismissed = remember { mutableStateOf(false) }

    when {
        locationPermissionsState.allPermissionsGranted ||
            locationPermissionsState.revokedPermissions.size == REQUIRED_NUMBER_OF_PERMISSION -> {
            onChangePermissionState()
        }
        isDismissed.value -> {
            PermissionDismissedCard(onGoToAppSettingsClick)
        }
        !locationPermissionsState.shouldShowRationale -> {
            val openDialogCustom = remember { mutableStateOf(true) }

            ForecastScreenContent(
                currentWeather = null,
                hourlyWeather = null,
                isLoading = false,
                dailyData = dailyData,
                onForecastDetails = {}
            )

            if (openDialogCustom.value) {
                RequestLocationDialog(
                    openDialogCustom = openDialogCustom,
                    isDismissed = isDismissed,
                    locationPermissionsState = locationPermissionsState,
                    onChangePermissionState = onChangePermissionState
                )
            }
        }
        else -> {
            PermissionDismissedCard(onGoToAppSettingsClick)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequestLocationDialog(
    openDialogCustom: MutableState<Boolean>,
    isDismissed: MutableState<Boolean>,
    locationPermissionsState: MultiplePermissionsState,
    onChangePermissionState: () -> Unit
) {
    Dialog(onDismissRequest = {
        openDialogCustom.value = false
        isDismissed.value = true
    }) {
        CustomDialog(
            onAllow = {
                openDialogCustom.value = false
                locationPermissionsState.launchMultiplePermissionRequest()
            },
            onDismiss = {
                openDialogCustom.value = false
                isDismissed.value = true
            },
            rationalBody = stringResource(R.string.location_rationale),
            contentColor = WeatherAppTheme.colors.requestPermissionCard,
            textColor = WeatherAppTheme.colors.requestPermissionText
        )
    }
    onChangePermissionState()
}

@Composable
private fun PermissionDismissedCard(
    onGoToAppSettingsClick: () -> Unit
) {
    MainCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            MainCardErrorInfo(
                title = stringResource(R.string.permission_dismiss_title),
                errorMessage = stringResource(R.string.permission_dismiss_rational),
                icon = ImageVector.vectorResource(id = R.drawable.ic_location),
                iconDescription = stringResource(R.string.permission_dismiss_title)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                PermissionDismissedCardButton(
                    onClick = { /*TODO navigate to choose location screen*/ },
                    clipShape = RoundedCornerShape(bottomStart = 30.dp),
                    backgroundColor = Brush.horizontalGradient(listOf(DarkRed, LightRed)),
                    buttonText = stringResource(R.string.choose_location)
                )
                PermissionDismissedCardButton(
                    onClick = onGoToAppSettingsClick,
                    clipShape = RoundedCornerShape(bottomEnd = 30.dp),
                    backgroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
                    buttonText = stringResource(R.string.grant_permission)
                )
            }
        }
    }
}

@Composable
private fun RowScope.PermissionDismissedCardButton(
    onClick: () -> Unit,
    clipShape: RoundedCornerShape,
    backgroundColor: Brush,
    buttonText: String
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .background(backgroundColor)
            .clip(clipShape)
            .clickable { onClick.invoke() }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp),
            text = buttonText,
            textAlign = TextAlign.Center,
            color = White,
            style = WeatherAppTheme.typography.mediumTitle
        )
    }
}

@Preview
@Composable
private fun PreviewLocationPermission() {
    LocationPermission(
        dailyData = null,
        onChangePermissionState = {},
        onGoToAppSettingsClick = {}
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun PreviewRequestLocationDialog() {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    val isDismissed = remember { mutableStateOf(false) }
    val openDialogCustom = remember { mutableStateOf(true) }

    RequestLocationDialog(
        openDialogCustom = openDialogCustom,
        isDismissed = isDismissed,
        locationPermissionsState = locationPermissionsState,
        onChangePermissionState = {}
    )
}

@Preview
@Composable
private fun PreviewPermissionDismissedCard() {
    PermissionDismissedCard {}
}

@Preview
@Composable
private fun PreviewPermissionDismissedCardButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        PermissionDismissedCardButton(
            onClick = { },
            clipShape = RoundedCornerShape(bottomStart = 30.dp),
            backgroundColor = Brush.horizontalGradient(listOf(DarkRed, LightRed)),
            buttonText = stringResource(R.string.choose_location)
        )
        PermissionDismissedCardButton(
            onClick = {},
            clipShape = RoundedCornerShape(bottomEnd = 30.dp),
            backgroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
            buttonText = stringResource(R.string.grant_permission)
        )
    }
}
