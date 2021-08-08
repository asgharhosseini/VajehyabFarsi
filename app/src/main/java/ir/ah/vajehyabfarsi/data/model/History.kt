package ir.ah.vajehyabfarsi.data.model


import androidx.room.*
import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
@Entity
data class History(
    @PrimaryKey(autoGenerate = true )
    @Json(name = "id")
    val id: Int=0,
    @Json(name = "title")
    val title: String,
    @Json(name = "filter")
    val filter: String,
)