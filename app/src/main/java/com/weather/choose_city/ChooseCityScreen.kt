package com.weather.choose_city

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.weather.R
import kotlinx.android.synthetic.main.activity_choose_city_screen.*
import weather_screen.CityWeatherScreen

class ChooseCityScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city_screen)

        city_listview.adapter = ArrayAdapter(this, R.layout.city_item, fillList())

        btnCancelSearch.setOnClickListener {
            val intent = Intent(this, CityWeatherScreen::class.java)
            startActivity( intent )
        }


    }
}

private fun fillList(): Array<String> {

    val cityList = arrayOf(
        "Омск", "Москва", "Краснодар", "Калининград", "Сочи",
        "Кемерово"
    )

    return cityList
}

//https://askdev.ru/q/polzovatelskiy-searchview-ves-klikabelnyy-v-android-379955/