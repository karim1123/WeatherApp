package karim.gabbasov.detailedforecast.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import karim.gabbasov.common.Mapper
import karim.gabbasov.detailedforecast.mapper.EntityToDisplayableDailyWeatherInfoMapper
import karim.gabbasov.detailedforecast.model.DisplayableDailyWeatherDataByTimeOfDay
import karim.gabbasov.model.data.weather.WeatherInfo
import kotlinx.collections.immutable.ImmutableList

@Suppress("UnusedPrivateClass")
@Module
@InstallIn(ViewModelComponent::class)
private interface MapperModule {

    @Binds
    @ViewModelScoped
    fun provideEntityToDisplayableDailyWeatherInfo(
        mapper: EntityToDisplayableDailyWeatherInfoMapper
    ): Mapper<
        @JvmSuppressWildcards WeatherInfo,
        @JvmSuppressWildcards ImmutableList<DisplayableDailyWeatherDataByTimeOfDay>
        >
}
