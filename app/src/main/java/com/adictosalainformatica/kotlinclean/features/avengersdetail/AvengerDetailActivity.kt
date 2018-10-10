package com.adictosalainformatica.kotlinclean.features.avengersdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Avenger
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_avenger_detail.*
import timber.log.Timber

class AvengerDetailActivity : AppCompatActivity() {

    private var avengerModel: Avenger? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avenger_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        intent.hasExtra("avenger").let {
            avengerModel = intent.getParcelableExtra("avenger")
            setValues()
        }

    }

    private fun setValues() {
        title = avengerModel?.avengerName

        detail_date.text = avengerModel?.avengerDateUpdate
        detail_title.text = "Id: ${avengerModel?.avengerName}"
        avenger_detail.text = avengerModel?.description
        Timber.d("image ${avengerModel?.imageUrl}")
        Picasso.with(applicationContext)
                .load(avengerModel?.imageUrl)
                .into(detail_image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
