package karim.gabbasov.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.weatherapp.navigation.WeatherNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(WeatherAppTheme.colors.background)
                ) {
                    val navController = rememberNavController()
                    WeatherNavHost(navController = navController)
                }
            }
        }
    }
}
