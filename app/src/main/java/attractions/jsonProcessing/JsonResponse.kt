package attractions.jsonProcessing

import com.google.gson.annotations.SerializedName

data class JsonResponse (
    @SerializedName("attractions")
    val list: List<PlaceItem>
    ) {
}