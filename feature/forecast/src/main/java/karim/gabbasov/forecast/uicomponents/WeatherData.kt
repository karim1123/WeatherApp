package karim.gabbasov.forecast.uicomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightGrey
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.designsystem.theme.White
import karim.gabbasov.ui.R

@Composable
internal fun WeatherDataDisplay(
    value: String,
    measure: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textColor: Color,
    secondTextColor: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = LightGrey,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            color = textColor,
            style = WeatherAppTheme.typography.value
        )
        Text(
            text = measure,
            color = secondTextColor,
            style = WeatherAppTheme.typography.weatherMeasure
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF102840)
@Composable
private fun PreviewWeatherDataDisplay() {
    WeatherDataDisplay(
        value = "1019 ",
        measure = stringResource(R.string.pressure_in_hpa),
        icon = ImageVector.vectorResource(R.drawable.ic_pressure),
        textColor = White,
        secondTextColor = LightGrey
    )
}
