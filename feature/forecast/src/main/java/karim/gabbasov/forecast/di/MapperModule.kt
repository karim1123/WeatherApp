package karim.gabbasov.forecast.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import karim.gabbasov.common.Mapper
import karim.gabbasov.forecast.mapper.EntityToDisplayableWeatherInfoMapper
import karim.gabbasov.forecast.model.DisplayableWeatherInfo
import karim.gabbasov.model.data.weather.WeatherInfo

@Module
@InstallIn(ViewModelComponent::class)
internal interface MapperModule {

    @Binds
    @ViewModelScoped
    fun provideEntityToDisplayableWeatherInfoMapper(
        mapper: EntityToDisplayableWeatherInfoMapper
    ): Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo>
}
