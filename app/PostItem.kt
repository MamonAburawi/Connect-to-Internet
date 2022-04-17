
import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("field")
    val `field`: String,
    @SerializedName("message")
    val message: String
)