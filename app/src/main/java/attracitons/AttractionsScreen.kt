package attracitons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.R
import kotlinx.android.synthetic.main.activity_attractions_screen.*
import weather_screen.WeatherListItem
import weather_screen.WeatherStatus

class AttractionsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions_screen)

        place_list.adapter = AttractionListViewAdapter(this.layoutInflater, fillList() )
    }


    private fun fillList(): List<PlaceItem> {

        val data = mutableListOf<PlaceItem>()
        data.add( PlaceItem("Музей естественных наук", resources.getString(R.string.lorem_ipsum), R.drawable.example_place) )
        data.add( PlaceItem("Музей естественных наук", resources.getString(R.string.lorem_ipsum), R.drawable.example_place) )
        data.add( PlaceItem("Музей естественных наук", resources.getString(R.string.lorem_ipsum), R.drawable.example_place) )
        data.add( PlaceItem("Музей естественных наук", resources.getString(R.string.lorem_ipsum), R.drawable.example_place) )
        data.add( PlaceItem("Музей естественных наук", resources.getString(R.string.lorem_ipsum), R.drawable.example_place) )
        return data
    }
}