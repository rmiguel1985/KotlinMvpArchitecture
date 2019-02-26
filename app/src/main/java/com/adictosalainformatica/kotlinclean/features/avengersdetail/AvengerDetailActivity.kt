package com.adictosalainformatica.kotlinclean.features.avengersdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Avenger
import com.adictosalainformatica.kotlinclean.utils.Constants.AVENGER_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_avenger_detail.*

class AvengerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avenger_detail)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        if (intent.hasExtra(AVENGER_KEY)) {
            setValues(intent.getParcelableExtra(AVENGER_KEY))
        }
    }

    private fun setValues(avengerModel: Avenger) {
        title = avengerModel.avengerName

        detail_date.text = avengerModel.avengerDateUpdate
        detail_title.text = "Id: ${avengerModel.avengerName}"
        avenger_detail.text = avengerModel.description
        Picasso.get()
                .load(avengerModel.imageUrl)
                .into(detail_image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
