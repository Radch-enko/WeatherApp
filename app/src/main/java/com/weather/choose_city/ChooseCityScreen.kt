package com.weather.choose_city

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.weather.R
import common.Config
import kotlinx.android.synthetic.main.activity_choose_city_screen.*
import weather_screen.CityWeatherScreen
import java.util.*

class ChooseCityScreen : AppCompatActivity() {

    private lateinit var viewModel: ChooseCityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city_screen)

        viewModel = ViewModelProvider(this).get(ChooseCityViewModel::class.java)
        city_listview.adapter = viewModel.getLastCitiesAdapter()

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.city_list_adapter?.filter?.filter(search_view.query.toString())
                viewModel.city_list_adapter?.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // если search view пустой, то показываем последний запросы
                if (search_view.query.toString().isEmpty()){
                    btnCancelSearch.visibility = View.GONE
                    city_listview.adapter = viewModel.getLastCitiesAdapter()
                }
                // а когда что-то вводим в поле поиска, то получаем города
                else {
                    btnCancelSearch.visibility = View.VISIBLE
                    city_listview.adapter = viewModel.city_list_adapter
                }

                // и фильтруем
                viewModel.city_list_adapter?.filter?.filter(search_view.query.toString())
                viewModel.city_list_adapter?.notifyDataSetChanged()
                return true
            }

        })


        btnCancelSearch.setOnClickListener {
            search_view.setQuery("", true)
        }

        // search view - ужасная штука
        // код для того, чтобы поле поиска активировалось при нажатии в любом месте поля
        // а не только на иконку лупы ( которой нет в дизайне, поэтому я ее скрыл )
        search_view.setOnClickListener {
            search_view.isIconified = false;
        }


        city_listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CityWeatherScreen::class.java)
            val tvCityItem = view.findViewById<TextView>(R.id.tvCityItem)

            Config.city.value = tvCityItem.text.toString()

            viewModel.saveLastCities(tvCityItem.text.toString())

            startActivity(intent)
        }

    }
}