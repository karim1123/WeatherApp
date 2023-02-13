package karim.gabbasov.ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import karim.gabbasov.ui.R.string.allow_permission
import karim.gabbasov.ui.R.string.dismiss_permission

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CustomPermissionDialog(
    openDialogCustom: MutableState<Boolean>,
    locationPermissionsState: MultiplePermissionsState,
    rationalBody: String
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialog(
            openDialogCustom = openDialogCustom,
            locationPermissionsState = locationPermissionsState,
            rationalBody = rationalBody
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    locationPermissionsState: MultiplePermissionsState,
    rationalBody: String
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = rationalBody,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth(),
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        stringResource(dismiss_permission),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = {
                    openDialogCustom.value = false
                    locationPermissionsState.launchMultiplePermissionRequest()
                }) {
                    Text(
                        stringResource(allow_permission),
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
