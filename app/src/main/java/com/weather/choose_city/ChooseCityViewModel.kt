package com.weather.choose_city

import android.app.Application
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.*
import com.weather.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ChooseCityViewModel(application: Application): AndroidViewModel(application) {

    val context = application.applicationContext

    val baseUrl = "https://gist.githubusercontent.com"

    // адаптер для обычного списка городов
    var city_list_adapter: ArrayAdapter<String>? = null

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(CityListAPI::class.java)
    val call = service.getCities()


    // использую сортед сет потому что
    // его можно легко сохранить в shared preferences
    private var last_cities: SortedSet<String>?

    init {
        last_cities = getLastCitiesSortedSet()
        if (last_cities.isNullOrEmpty())
            last_cities = sortedSetOf()

        // получаем города по апи
        getCities()
    }

    private fun getCities(): ArrayAdapter<String>? {
        var list: ArrayAdapter<String>? = null
        call.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.code() == 200){
                    city_list_adapter = prepareListCities(response.body())
                }
            }
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.e("TAG", "${t.message}")
            }

        })
        return list
    }

    private fun prepareListCities(list_cities: List<City>?): ArrayAdapter<String>? {
        /***
         * Преобразуем list в Array Adapter
         * ***/
        val res = mutableListOf<String>()

        list_cities?.forEach {
            res.add(it.city)
        }

        val adapter = ArrayAdapter(context, R.layout.city_item, res)

        return adapter
    }

    fun saveLastCities(selectedCity: String) {
        val sh = context.getSharedPreferences("lastCities", 0)
        val editor = sh.edit()

//        lastCities.value?.add(selectedCity)
        last_cities?.add(selectedCity)
        last_cities?.headSet(selectedCity)

        editor.putStringSet("cities", last_cities)
        editor.commit()
        editor.apply()
    }

    private fun getLastCitiesSortedSet(): SortedSet<String>? {
        /***
         * Получение из памяти последних городов
         * ***/
        val sh = context.getSharedPreferences("lastCities", 0)

        val set: Set<String>? = sh.getStringSet("cities", null)

        return set?.toSortedSet()
    }

    fun getLastCitiesAdapter(): ArrayAdapter<String>? {
        /***
         * Получение адапетра для list view
         * со списком последних городов
         * ***/
        val list = getLastCitiesSortedSet()?.toMutableList()
        if (list!=null)
            return ArrayAdapter(context, R.layout.last_city_item, list )

        return null
    }

}