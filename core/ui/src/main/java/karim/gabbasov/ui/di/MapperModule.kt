package karim.gabbasov.ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.common.Mapper
import karim.gabbasov.ui.mapper.WeatherCodeAndDateToWeatherTypeMapper
import karim.gabbasov.ui.model.WeatherType
import java.time.LocalDateTime

@Module
@InstallIn(SingletonComponent::class)
private interface MapperModule {

    @Binds
    fun provideWeatherCodeAndDateToWeatherTypeMapper(
        mapper: WeatherCodeAndDateToWeatherTypeMapper
    ): Mapper<@JvmSuppressWildcards Pair<Int, LocalDateTime>, WeatherType>
}
