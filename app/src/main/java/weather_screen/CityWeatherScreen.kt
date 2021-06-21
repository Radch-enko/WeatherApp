package weather_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.R

class CityWeatherScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather_screen)
    }
}