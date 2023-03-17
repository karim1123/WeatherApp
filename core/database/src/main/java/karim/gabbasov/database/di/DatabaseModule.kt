package karim.gabbasov.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.database.WeatherForecastDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        WeatherForecastDatabase.build(context)

    @Provides
    @Singleton
    fun provideWeather(db: WeatherForecastDatabase) = db.weatherData()
}
