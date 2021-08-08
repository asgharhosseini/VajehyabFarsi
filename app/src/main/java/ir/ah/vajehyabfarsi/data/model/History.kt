package ir.ah.vajehyabfarsi.data.model


import androidx.room.*
import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
@Entity
data class History(
    @Json(name = "db")
    val db: String,
    @PrimaryKey( )
    @Json(name = "id")
    val id: String,
    @Json(name = "num")
    val num: Int,
    @Json(name = "pron")
    val pron: String,
    @Json(name = "source")
    val source: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "title_en")
    val titleEn: String
)