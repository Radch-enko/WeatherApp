package weather_screen.other

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.weather.R
import common.Config
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
data class WeatherListItem(var time: String, var status: String, var icon: Int, var isTodayWeatherItem: Boolean? = false, var weatherValue: String) {

    val TAG = "TAG"

    init {

        // весь этот велосипед из-за того, что я не разобрался как выбрать часовой пояс в api
        // если бы в api время было того города, который указываешь, было бы проще


        /***
         *
         * 1 - получить время на моем компе
         * 2 - получить часовой пояс из api
         * 3 - изменить время на компе в соответствии с часовым поясом
         * 4 - использовать измененное время в if - блоке для вывода weather item
         *
         * ***/

        /***
         * 1 - у меня 3 часа ночи
         * 2 - api говорит что у Владивостока с миром разница в 10 часов
         * 3 - у Омска с миром разница 6 часов
         * 4 - 10 - 6 = 4
         * 5 - У Омска с Владивостком разница 4 часа
         * 6 - Омск. время + 4 часа = Владивосток. время
         */
        val diff = (Config.timeZone!!.value!!/60 - Math.abs(Calendar.getInstance().time.timezoneOffset) ) / 60


        // 2021-06-24 21:00:00

        // время с сервака
        val remoteDate = LocalDateTime.parse(time, Config.utcFormat)
        // время в UTC формате с сервака
        val remoteTimeZonedUTC: ZonedDateTime = remoteDate.atZone(ZoneId.of("UTC"))


        // время на моем компе в UTC
        var zonedUTC = Config.localNow

        Config.zonedTime.value = zonedUTC.plusHours(diff.toLong())

        zonedUTC = Config.zonedTime.value!!

        time = time.substring(10, 16).trim()



        if ( zonedUTC.format(Config.dtf) == remoteTimeZonedUTC.format(Config.dtf) )
            isTodayWeatherItem = true

        else if ( zonedUTC.plusDays(1).format(Config.dtf) == remoteTimeZonedUTC.format(Config.dtf) )
            isTodayWeatherItem = false

        else isTodayWeatherItem = null

        // дизайн немного не совпадет с апи
        // поэтому я решил вот так:
        // облачно = облачно
        // дождь = гроза
        // чисто = солнечно

        status = when (status){
            "Clouds" -> {
                icon = R.drawable.cloudy
                WeatherStatus.CLOUDY.status
            }
            "Rain" -> {
                icon = R.drawable.storm
                WeatherStatus.STORM.status
            }
            "Clear" -> {
                icon = R.drawable.sunny
                WeatherStatus.SUNNY.status
            }
            else -> null.toString()
        }


        weatherValue+=" С°"
    }

}