package attractions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.weather.R
import kotlinx.android.synthetic.main.activity_attractions_screen.*

class AttractionsScreen : AppCompatActivity(){

    lateinit var attractionsViewModel: AttractionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions_screen)

        attractionsViewModel = ViewModelProvider(this).get(AttractionsViewModel::class.java)

        place_list.adapter = AttractionListViewAdapter(this, attractionsViewModel.getAttractionsList())

        btnClose.setOnClickListener {
            finish()
        }
    }
}