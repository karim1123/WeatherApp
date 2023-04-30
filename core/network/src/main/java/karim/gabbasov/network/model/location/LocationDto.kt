package karim.gabbasov.network.model.location

import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "data")
    val data: LocationDataDto
)
