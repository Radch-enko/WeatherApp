package weather_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weather.R

class RecyclerViewAdapter (val list: List<WeatherListItem> ):
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weather_item_time: TextView? = null
        var weather_item_status: TextView? = null
        var weather_item_icon: ImageView? = null

        init {
            weather_item_time = itemView.findViewById(R.id.weather_item_time)
            weather_item_status = itemView.findViewById(R.id.weather_item_status)
            weather_item_icon = itemView.findViewById(R.id.weather_item_icon)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.weather_item_time?.text = list[position].time as CharSequence?
        holder.weather_item_status?.text = list[position].status as CharSequence?
        holder.weather_item_icon?.setImageResource( list[position].icon )
    }

    override fun getItemCount() = list.size

}