package com.weather.choose_city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weather.R
import kotlinx.android.synthetic.main.activity_choose_city_screen.*;
import weather_screen.CityWeatherScreen


class ChooseCityScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city_screen)

        btnCancelSearch.setOnClickListener {
            val intent = Intent(this, CityWeatherScreen::class.java)
            startActivity( intent )
        }
    }
}

//https://askdev.ru/q/polzovatelskiy-searchview-ves-klikabelnyy-v-android-379955/