package ir.ah.vajehyabfarsi.data.model.response


import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "code")
    val code: Int,
    @Json(name = "state")
    val state: Any,
    @Json(name = "status")
    val status: Boolean
)