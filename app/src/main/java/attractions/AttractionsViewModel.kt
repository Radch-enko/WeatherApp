package attractions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import attractions.jsonProcessing.AttractionsJSONParser
import attractions.jsonProcessing.PlaceItem


class AttractionsViewModel(application: Application): AndroidViewModel(application) {

    val context = application.applicationContext

    lateinit var attractionsJSONParser: AttractionsJSONParser

    fun getAttractionsList(): MutableList<PlaceItem> {
        attractionsJSONParser = AttractionsJSONParser(context)

        return attractionsJSONParser.parse()
    }

}