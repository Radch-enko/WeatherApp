package weather_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import attractions.AttractionsScreen
import com.weather.R

import common.Config
import kotlinx.android.synthetic.main.activity_city_weather_screen.*
import weather_screen.other.RecyclerViewAdapter
import weather_screen.other.WeatherListItem


class CityWeatherScreen : AppCompatActivity()  {

    lateinit var cityWeatherViewModel: CityWeatherViewModel

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather_screen)


        cityWeatherViewModel = ViewModelProvider(this).get(CityWeatherViewModel::class.java)

        val layoutManagerToday = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        today_weather_list.layoutManager = layoutManagerToday

        val layoutManagerTomorrow = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tomorrow_weather_list.layoutManager = layoutManagerTomorrow

        cityWeatherViewModel.hourlyWeather.observe(this, Observer {

            val remoteList = cityWeatherViewModel.getHourlyWeatherOnToday()!!

            val todayList = mutableListOf<WeatherListItem>()
            val tomorrowList = mutableListOf<WeatherListItem>()

            remoteList.forEach {
                if ( it.isTodayWeatherItem != null ){
                    if ( it.isTodayWeatherItem == true )
                        todayList.add(it)
                    else if ( it.isTodayWeatherItem == false )
                        tomorrowList.add(it)
                }
            }

            today_weather_list.adapter = RecyclerViewAdapter( todayList )
            tomorrow_weather_list.adapter = RecyclerViewAdapter( tomorrowList )
        })


        btnToAttractions.setOnClickListener {
            val intent = Intent(this, AttractionsScreen::class.java)
            startActivity( intent )
        }

        btnClose.setOnClickListener {
            finish()
        }

        Config.city.observe(this, Observer {
            tvHeaderTitle.text = Config.city.value
            tvCityName.text = Config.city.value
        })

        Config.zonedTime.observe(this, Observer {
            tvTodayDate.text = "Сегодня, ${it?.format(Config.dtf_timed).toString()}"
            tvTomorrowDate.text = "Завтра, ${it?.plusDays(1)?.format(Config.dtf_timed).toString()}"
        })
    }


}