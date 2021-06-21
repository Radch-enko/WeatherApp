package attracitons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.weather.R


class AttractionListViewAdapter (val inflater: LayoutInflater, val attractions: List<PlaceItem>): BaseAdapter() {

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
            view = inflater.inflate(R.layout.place_item, parent, false);
        }

        val curPlace = attractions[position]

        convertView?.findViewById<TextView>(R.id.tvPlaceName)?.text = curPlace.title
        convertView?.findViewById<TextView>(R.id.tvPlaceAbout)?.text = curPlace.about
        convertView?.findViewById<ImageView>(R.id.background_image)?.setImageResource(curPlace.background)

        return view
    }
}