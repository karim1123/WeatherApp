package karim.gabbasov.network.model.location

import com.squareup.moshi.Json

data class LocationSuggestionsDto(
    @field:Json(name = "suggestions")
    val suggestions: List<LocationDto>
)
