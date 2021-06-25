package weather_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.R
import common.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import weather_screen.other.WeatherListItem
import weather_screen.other.hourlyResponseClasses.HourlyWeather
import java.time.ZonedDateTime
import java.util.*


class CityWeatherViewModel: ViewModel() {

    var weatherLogic: OpenWeatherLogic = OpenWeatherLogic()

    var hourlyWeather = MutableLiveData<HourlyWeather>()

    var weatherItems: MutableLiveData< MutableList<WeatherListItem> > = MutableLiveData< MutableList<WeatherListItem> >()

    init {
        weatherItems.value = mutableListOf()
        weatherLogic.configureRetrofit()
        getWeatherList(weatherLogic.openWeatherMapApi)
    }

    private fun getWeatherList( owmApi: OpenWeatherMapAPI ){

        val list = owmApi.getCityHourlyWeather(Config.city.value!!, "metric", "ru" , Config.api_key)
        list.enqueue(object : Callback<HourlyWeather>{
            override fun onResponse(call: Call<HourlyWeather>, response: Response<HourlyWeather>) {
                if (response.code() == 200){
                    println("response = ${response.body()}")
                    hourlyWeather.value = response.body()
//                    Config.timeZone = hourlyWeather?.value?.city?.timezone
                }
            }

            override fun onFailure(call: Call<HourlyWeather>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHourlyWeatherOnToday(): MutableList<WeatherListItem>? {
        Config.timeZone.value = hourlyWeather.value?.city?.timezone
        Config.coord.value = hourlyWeather.value?.city?.coord

        val list = mutableListOf<WeatherListItem>()
        hourlyWeather.value?.list?.forEach {
            list.add( WeatherListItem( it.dt_txt, it.weather[0].main, R.drawable.cloudy, false, it.main.temp.toInt().toString()) )
        }
        return list
    }
}