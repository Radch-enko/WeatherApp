package com.weather.choose_city

import retrofit2.Call
import retrofit2.http.GET

interface CityListService {
    @GET("/gorborukov/0722a93c35dfba96337b/raw/435b297ac6d90d13a68935e1ec7a69a225969e58/russia")
    fun getCities(): Call<List<City>>
}