package karim.gabbasov.network.model.location

import com.squareup.moshi.Json

data class LocationDataDto(
    @field:Json(name = "city")
    val city: String
)
