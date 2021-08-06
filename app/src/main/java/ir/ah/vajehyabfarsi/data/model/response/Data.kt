package ir.ah.vajehyabfarsi.data.model.response


import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "num_found")
    val numFound: Int,
    @Json(name = "results")
    val results: List<Vajeh>
)