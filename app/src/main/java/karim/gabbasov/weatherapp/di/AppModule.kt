package karim.gabbasov.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.api.features.DetailedForecastFeatureApi
import karim.gabbasov.api.features.ForecastFeatureApi
import karim.gabbasov.detailedforecast.navigation.DetailedForecastNavigation
import karim.gabbasov.forecast.navigation.ForecastNavigation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private interface AppModule {

    @Binds
    @Singleton
    fun bindsFeaturesApi(
        forecastNavigation: ForecastNavigation
    ): ForecastFeatureApi

    @Binds
    @Singleton
    fun bindsDetailedForecastFeatureApi(
        detailedForecastNavigation: DetailedForecastNavigation
    ): DetailedForecastFeatureApi
}
