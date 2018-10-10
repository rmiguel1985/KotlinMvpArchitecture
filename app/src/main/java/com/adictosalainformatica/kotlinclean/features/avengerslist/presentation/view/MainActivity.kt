package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.adict.AvengersListAdapter
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.features.avengersdetail.AvengerDetailActivity
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Avenger
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.AvengersListPresenterView
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.PresenterImpl
import com.adictosalainformatica.kotlinclean.utils.Constants.AVENGER_KEY
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), AvengersListPresenterView, AvengersListAdapter.OnAvengerListItemClickedListener {


    private val avengersListPresenter: PresenterImpl by inject()
    private var adapter: AvengersListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        avengersListPresenter.attachView(this, lifecycle)

        createAvengersListAdapter()
        avengersListPresenter.loadAvengers()
    }

    private fun createAvengersListAdapter() {
        adapter = AvengersListAdapter(applicationContext)
        adapter!!.setOnAvengerListItemClickedListener(this)
        avenger_list_recyclerview.layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        avenger_list_recyclerview.itemAnimator = DefaultItemAnimator()
        avenger_list_recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        avenger_list_recyclerview.adapter = adapter
    }

    override fun showProgress() {
        avengerslist_progressbar_list.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        avengerslist_progressbar_list.visibility = View.GONE
    }

    override fun onAvengersListLoaded(avengersList: List<Result>) {
        Timber.d("Avengers list loaded")
        adapter!!.setAvengersList(avengersList)
    }

    override fun showErrorLoadingAvengersList() {
        Timber.e("Error loading avengers list")
        Toast.makeText(this, "Error loading avengers list", Toast.LENGTH_LONG).show()
    }

    override fun onAvengerListItemClicked(avengerModel: Result) {
        Timber.d("avenger name: " + avengerModel.name)
        val intent = Intent(applicationContext, AvengerDetailActivity::class.java)
        var avenger = Avenger(
                (avengerModel.name!!),
                (avengerModel.modified!!),
                (avengerModel.thumbnail!!.path + "." + avengerModel!!.thumbnail!!.extension),
                (avengerModel.description!!))

        intent.putExtra(AVENGER_KEY, avenger)
        startActivity(intent)
    }
}
