package weather_screen.other.hourlyResponseClasses

import City
import Hourlist
import com.google.gson.annotations.SerializedName


data class HourlyWeather (
    @SerializedName("cod") val cod : Int,
    @SerializedName("message") val message : Int,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<Hourlist>,
    @SerializedName("city") val city : City
        ) {
}