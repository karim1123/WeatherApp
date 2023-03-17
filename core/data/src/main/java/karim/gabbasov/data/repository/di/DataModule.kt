package karim.gabbasov.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import karim.gabbasov.data.repository.LocationTracker
import karim.gabbasov.data.repository.LocationTrackerImpl
import karim.gabbasov.data.repository.WeatherRepository
import karim.gabbasov.data.repository.WeatherRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
private interface DataModule {

    @Binds
    @ViewModelScoped
    fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    @ViewModelScoped
    fun bindLocationTracker(locationTrackerImpl: LocationTrackerImpl): LocationTracker
}
