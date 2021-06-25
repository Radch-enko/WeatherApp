package weather_screen.openWeatherMapAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherLogic {

    lateinit var openWeatherMapApi: OpenWeatherMapAPI

    fun configureRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        openWeatherMapApi = retrofit.create(OpenWeatherMapAPI::class.java)
    }
}