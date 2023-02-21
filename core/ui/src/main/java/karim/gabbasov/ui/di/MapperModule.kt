package karim.gabbasov.ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.common.Mapper
import karim.gabbasov.model.data.weather.WeatherInfo
import karim.gabbasov.ui.mapper.EntityToDisplayableWeatherInfoMapper
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherTypeMapper
import karim.gabbasov.ui.model.DisplayableWeatherInfo
import karim.gabbasov.ui.model.WeatherType
import java.time.LocalDateTime

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    fun provideWeatherCodeAndDateToWeatherTypeMapper(
        mapper: WeatherCodeAndDateToWeatherTypeMapper
    ): Mapper<@JvmSuppressWildcards Pair<Int, LocalDateTime>, WeatherType>

    @Binds
    fun provideEntityToDisplayableWeatherInfoMapper(
        mapper: EntityToDisplayableWeatherInfoMapper
    ): Mapper<@JvmSuppressWildcards WeatherInfo, DisplayableWeatherInfo>
}
