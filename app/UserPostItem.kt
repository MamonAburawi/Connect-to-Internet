
import com.google.gson.annotations.SerializedName

data class UserPostItem(
    @SerializedName("field")
    val `field`: String,
    @SerializedName("message")
    val message: String
)