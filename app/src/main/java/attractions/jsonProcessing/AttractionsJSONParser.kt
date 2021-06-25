package attractions.jsonProcessing

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import common.Config
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class AttractionsJSONParser(val context: Context) {

    val gson = Gson()

    private fun getJson(): String {
        val ins: InputStream = context.getResources().openRawResource(
            context.getResources().getIdentifier(
                "json",
                "raw", context.getPackageName()
            )
        )

        val response = BufferedReader(
            InputStreamReader(ins, "UTF-8")
        ).use { it.readText() }

        return response
    }

    fun parse(): MutableList<PlaceItem> {
        val arrayPlaceItemsType = object : TypeToken<JsonResponse>() {}.type

        val jsonResponse: JsonResponse = gson.fromJson(getJson(), arrayPlaceItemsType)

        val list = mutableListOf<PlaceItem>()

        jsonResponse.list.forEach {
            if (it.city == Config.city.value)
                list.add(it)
        }
        return list
    }
}