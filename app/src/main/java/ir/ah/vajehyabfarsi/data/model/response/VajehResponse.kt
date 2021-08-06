package ir.ah.vajehyabfarsi.data.model.response


import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class VajehResponse(
    @Json(name = "data")
    val data: Data,
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "response")
    val response: Response
)