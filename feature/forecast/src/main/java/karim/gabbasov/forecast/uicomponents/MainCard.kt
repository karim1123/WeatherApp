package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import karim.gabbasov.designsystem.theme.DarkCurrentWeatherCard
import karim.gabbasov.designsystem.theme.LightCurrentWeatherCard
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.ui.R

@Composable
internal fun MainCard(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(WeatherAppTheme.shapes.CurrentWeatherCard)
            .background(
                Brush.verticalGradient(listOf(LightCurrentWeatherCard, DarkCurrentWeatherCard))
            )
    ) {
        content()
    }
}

@Composable
internal fun MainCardErrorInfo(
    title: String,
    errorMessage: String,
    icon: ImageVector,
    iconDescription: String
) {
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
        modifier = Modifier.padding(horizontal = 12.dp),
        text = title,
        textAlign = TextAlign.Center,
        color = White,
        style = WeatherAppTheme.typography.mediumTitle
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        modifier = Modifier.padding(horizontal = 12.dp),
        text = errorMessage,
        textAlign = TextAlign.Justify,
        color = LightGrey,
        style = WeatherAppTheme.typography.mediumTitle
    )
}

@Preview
@Composable
private fun PreviewMainCard() {
    MainCard {}
}

@Preview
@Composable
private fun PreviewMainCardErrorInfo() {
    MainCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainCardErrorInfo(
                title = stringResource(R.string.gps_off_title),
                errorMessage = stringResource(R.string.gps_error_message),
                icon = ImageVector.vectorResource(id = R.drawable.ic_location),
                iconDescription = stringResource(R.string.gps_off_title)
            )
        }
    }
}
