package attractions.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import attractions.jsonProcessing.PlaceItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.weather.R
import kotlinx.android.synthetic.main.activity_attractions_screen.btnClose
import kotlinx.android.synthetic.main.activity_place_detail_screen.*

class PlaceDetailScreen : AppCompatActivity() {

    val gson = Gson()

    lateinit var curPlace: PlaceItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail_screen)

        val jsonCurPlace = intent.getStringExtra("jsonCurPlace")

        curPlace = gson.fromJson(jsonCurPlace, PlaceItem::class.java)

        Log.e("TAG", curPlace.toString())

        tvHeaderTitle.text = curPlace.name
        Picasso.get().load(curPlace.images).placeholder(R.drawable.no_image).into(findViewById<ImageView>(R.id.place_image))
        tvPlaceName.text = curPlace.name
        tvPlaceAbout.text = curPlace.desc


        btnReadMore.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                tvPlaceAbout.text = curPlace.descfull
            else
                tvPlaceAbout.text = curPlace.desc
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

}