package weather_screen.openWeatherMapAPI


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import weather_screen.other.hourlyResponseClasses.HourlyWeather

interface OpenWeatherMapAPI {
    @GET("forecast?")
    fun getCityHourlyWeather(
        @Query ("q") cityName: String,
        @Query ("units") units: String,
//        @Query ("cnt") cnt: String,
        @Query ("lang") lang: String,
        @Query ("APPID") key: String): Call<HourlyWeather>
}