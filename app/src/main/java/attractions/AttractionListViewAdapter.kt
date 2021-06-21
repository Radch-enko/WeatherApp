package attractions

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import attractions.detail.PlaceDetailScreen
import com.weather.R


class AttractionListViewAdapter (val context: Context , val attractions: List<PlaceItem>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

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

        view?.findViewById<TextView>(R.id.tvPlaceName)?.text = curPlace.title
        view?.findViewById<TextView>(R.id.tvPlaceAbout)?.text = curPlace.about
        view?.findViewById<ImageView>(R.id.background_image)?.setImageResource(curPlace.background)

        view?.setOnClickListener {
            val intent = Intent(context, PlaceDetailScreen::class.java )
            context.startActivity(intent)
        }

        return view
    }
}