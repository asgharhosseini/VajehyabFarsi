package ir.ah.vajehyabfarsi.data.model.response


import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "filter")
    val filter: String,
    @Json(name = "q")
    val q: String,
    @Json(name = "type")
    val type: String
)