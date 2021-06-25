package attractions.jsonProcessing

import weather_screen.other.hourlyResponseClasses.Coord
import java.io.Serializable

data class PlaceItem (val name: String, val city: String, val images: String,  val desc: String, val descfull: String, val geo: Coord): Serializable {
}