package common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import weather_screen.other.WeatherListItem
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Config {
    companion object{
        var city = MutableLiveData<String>()
        var timeZone = MutableLiveData<Int>()
        const val api_key = "b565c95da58582be73fe68a8aa7331a7"
        var zonedTime = MutableLiveData<LocalDateTime>()

        @RequiresApi(Build.VERSION_CODES.O)
        val localNow: LocalDateTime = LocalDateTime.now()

        @RequiresApi(Build.VERSION_CODES.O)
        val utcFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("ru"))

        @RequiresApi(Build.VERSION_CODES.O)
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        @RequiresApi(Build.VERSION_CODES.O)
        val dtf_timed = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")
    }
}