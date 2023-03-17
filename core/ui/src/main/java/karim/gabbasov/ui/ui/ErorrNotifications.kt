package karim.gabbasov.ui.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.DarkRed
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.ui.R.drawable.ic_mobile_tower
import karim.gabbasov.ui.R.string.no_network_error_subtitle
import karim.gabbasov.ui.R.string.no_network_error_title
import karim.gabbasov.ui.R.string.snackbar_network_error_content
import karim.gabbasov.ui.R.string.update

@Composable
fun ErrorSnackbar(
    modifier: Modifier,
    backGroundColor: Brush,
    textColor: Color,
    onClick: () -> Unit,
    snackbarState: Boolean,
    @StringRes buttonText: Int,
    @StringRes messageText: Int
) {
    val snackbarVisibleState by remember { mutableStateOf(snackbarState) }

    if (snackbarVisibleState) {
        Row(
            modifier = modifier
                .background(backGroundColor)
                .defaultMinSize(minHeight = 60.dp)
                .padding(start = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(2f),
                color = textColor,
                text = stringResource(messageText)
            )
            Button(
                modifier = Modifier
                    .defaultMinSize(minHeight = 70.dp)
                    .wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RectangleShape,
                onClick = {
                    onClick.invoke()
                    !snackbarVisibleState
                }
            ) {
                Text(
                    modifier = Modifier.wrapContentWidth(),
                    color = textColor,
                    text = stringResource(buttonText),
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    errorMessage: String,
    icon: ImageVector,
    iconDescription: String,
    backGroundColor: Brush
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = WeatherAppTheme.colors.currentWeatherCard
        ),
        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(White)
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                color = White,
                style = WeatherAppTheme.typography.mediumTitle
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = errorMessage,
                color = LightGrey,
                style = WeatherAppTheme.typography.mediumTitle
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                shape = RoundedCornerShape(20.dp),
                onClick = { onClick.invoke() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
            ) {
                Box(
                    modifier = Modifier
                        .background(backGroundColor)
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                ) {
                    Text(
                        color = Color.White,
                        text = stringResource(update)
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview
@Composable
fun PreviewErrorSnackbar() {
    ErrorSnackbar(
        modifier = Modifier,
        backGroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
        textColor = Color.White,
        onClick = { },
        snackbarState = true,
        buttonText = update,
        messageText = snackbar_network_error_content
    )
}

@Preview
@Composable
fun PreviewErrorCard() {
    ErrorCard(
        onClick = {},
        backGroundColor = Brush.horizontalGradient(listOf(LightRed, DarkRed)),
        title = stringResource(no_network_error_title),
        errorMessage = stringResource(no_network_error_subtitle),
        icon = ImageVector.vectorResource(ic_mobile_tower),
        iconDescription = stringResource(no_network_error_title)
    )
}
