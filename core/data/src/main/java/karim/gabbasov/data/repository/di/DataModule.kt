package karim.gabbasov.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.data.repository.LocationTracker
import karim.gabbasov.data.repository.LocationTrackerImpl
import karim.gabbasov.data.repository.WeatherRepository
import karim.gabbasov.data.repository.WeatherRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    fun bindLocationTracker(locationTrackerImpl: LocationTrackerImpl): LocationTracker
}
