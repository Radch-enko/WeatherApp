package weather_screen.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weather.R

class RecyclerViewAdapter (val list: MutableList<WeatherListItem> ):
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weather_item_time: TextView? = null
        var weather_item_status: TextView? = null
        var tvWeatherValue: TextView? = null
        var weather_item_icon: ImageView? = null

        init {
            weather_item_time = itemView.findViewById(R.id.weather_item_time)
            weather_item_status = itemView.findViewById(R.id.weather_item_status)
            tvWeatherValue = itemView.findViewById(R.id.tvWeatherValue)
            weather_item_icon = itemView.findViewById(R.id.weather_item_icon)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.weather_item_time?.text = list[position].time
        holder.weather_item_status?.text = list[position].status
        holder.tvWeatherValue?.text = list[position].weatherValue
        holder.weather_item_icon?.setImageResource( list[position].icon )
    }

    override fun getItemCount() = list.size

}