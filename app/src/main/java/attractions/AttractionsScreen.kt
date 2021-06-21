package attractions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.weather.R
import kotlinx.android.synthetic.main.activity_attractions_screen.*

class AttractionsScreen : AppCompatActivity() {

    var adapter: AttractionListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions_screen)

        adapter = AttractionListViewAdapter(this , fillList() )
        adapter?.notifyDataSetChanged()
        place_list.adapter = adapter

        place_list.setAnimationCacheEnabled(false)
        place_list.setScrollingCacheEnabled(false)


    }


    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
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