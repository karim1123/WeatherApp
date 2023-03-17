package karim.gabbasov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherForecastEntity::class], version = 1)
internal abstract class WeatherForecastDatabase : RoomDatabase() {

    internal abstract fun weatherData(): WeatherForecastDao

    internal companion object {
        /**
         * Builds the database for usage.
         */
        fun build(
            context: Context,
            databaseName: String = "weather_database.db"
        ): WeatherForecastDatabase {
            return Room
                .databaseBuilder(context, WeatherForecastDatabase::class.java, databaseName)
                .build()
        }
    }
}
