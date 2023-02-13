package karim.gabbasov.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherData: List<WeatherForecastEntity>)

    @Query("SELECT * FROM weather_data")
    fun getWeatherData(): Flow<List<WeatherForecastEntity>>

    @Query("SELECT COUNT(*) FROM weather_data")
    suspend fun count(): Int
}
