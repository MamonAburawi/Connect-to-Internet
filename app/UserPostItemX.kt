
import com.google.gson.annotations.SerializedName

data class UserPostItemX(
    @SerializedName("field")
    val `field`: String,
    @SerializedName("message")
    val message: String
)