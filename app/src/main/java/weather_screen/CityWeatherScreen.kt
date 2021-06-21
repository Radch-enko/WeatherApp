package weather_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import attractions.AttractionsScreen
import com.weather.R
import kotlinx.android.synthetic.main.activity_attractions_screen.*
import kotlinx.android.synthetic.main.activity_city_weather_screen.*
import kotlinx.android.synthetic.main.activity_city_weather_screen.btnClose


class CityWeatherScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather_screen)

        val layoutManagerToday = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        today_weather_list.layoutManager = layoutManagerToday
        today_weather_list.adapter = RecyclerViewAdapter(fillList())

        val layoutManagerTomorrow = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tomorrow_weather_list.layoutManager = layoutManagerTomorrow
        tomorrow_weather_list.adapter = RecyclerViewAdapter(fillList())

        btnToAttractions.setOnClickListener {
            val intent = Intent(this, AttractionsScreen::class.java)
            startActivity( intent )
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun fillList(): List<WeatherListItem> {

        val data = mutableListOf<WeatherListItem>()
        data.add(WeatherListItem("12:00", WeatherStatus.CLOUDY.status, R.drawable.cloudy))
        data.add(WeatherListItem("13:00", WeatherStatus.CLOUDY.status, R.drawable.cloudy))
        data.add(WeatherListItem("14:00", WeatherStatus.CLOUDY.status, R.drawable.cloudy))
        data.add(WeatherListItem("15:00", WeatherStatus.STORM.status, R.drawable.storm))
        data.add(WeatherListItem("16:00", WeatherStatus.STORM.status, R.drawable.storm))
        data.add(WeatherListItem("16:00", WeatherStatus.STORM.status, R.drawable.storm))
        data.add(WeatherListItem("17:00", WeatherStatus.SUNNY.status, R.drawable.sunny))
        data.add(WeatherListItem("18:00", WeatherStatus.SUNNY.status, R.drawable.sunny))
        data.add(WeatherListItem("19:00", WeatherStatus.SUNNY.status, R.drawable.sunny))
        data.add(WeatherListItem("20:00", WeatherStatus.SUNNY.status, R.drawable.sunny))
        data.add(WeatherListItem("21:00", WeatherStatus.SUNNY.status, R.drawable.sunny))

        return data
    }
}