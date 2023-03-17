package karim.gabbasov.detailed_forecast.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.ui.R.string.detailed_forecast_top_bar
import karim.gabbasov.ui.R.string.detailed_forecast_top_bar_nav_desc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(detailed_forecast_top_bar),
                style = WeatherAppTheme.typography.topBarTitle,
                color = WeatherAppTheme.colors.dailyForecastText
            )
        },
        navigationIcon = {
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                IconButton(
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = 20.dp),
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(detailed_forecast_top_bar_nav_desc),
                        tint = LightRed
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WeatherAppTheme.colors.forecastDetailsBackground
        )
    )
}

@Preview
@Composable
private fun PreviewTopBar() {
    TopBar {}
}
