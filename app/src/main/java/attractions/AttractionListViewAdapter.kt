package attractions

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import attractions.detail.PlaceDetailScreen
import attractions.jsonProcessing.PlaceItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.weather.R


class AttractionListViewAdapter (val context: Context , val attractions: List<PlaceItem>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    val gson = Gson()

    override fun getCount(): Int {
        return attractions.size
    }

    override fun getItem(position: Int): Any {
        return attractions[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view == null) {
            view = mInflater.inflate(R.layout.place_item, parent, false);
        }

        val curPlace = attractions[position]
        val jsonCurPlace = gson.toJson(curPlace)

        view?.findViewById<TextView>(R.id.tvPlaceName)?.text = curPlace.name
        view?.findViewById<TextView>(R.id.tvPlaceAbout)?.text = curPlace.desc
        Picasso.get().load(curPlace.images).placeholder(R.drawable.no_image).into(view?.findViewById(R.id.background_image))

        view?.setOnClickListener {
            val intent = Intent(context, PlaceDetailScreen::class.java )
            intent.putExtra("jsonCurPlace", jsonCurPlace)
            context.startActivity(intent)
        }

        return view
    }
}