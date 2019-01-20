package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.base.presentation.BaseActivity
import com.adictosalainformatica.kotlinclean.features.avengersdetail.AvengerDetailActivity
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Avenger
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.AvengersListPresenterView
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.ListAvengerPresenterImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view.adapter.AvengersListAdapter
import com.adictosalainformatica.kotlinclean.utils.Constants.AVENGER_KEY
import com.adictosalainformatica.kotlinclean.utils.Constants.AVENGER_LIST_PRESENTER
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import timber.log.Timber

class MainActivity : BaseActivity(), AvengersListPresenterView, AvengersListAdapter.OnAvengerListItemClickedListener {

    private val avengersListPresenter: ListAvengerPresenterImpl by inject()
    private var adapter: AvengersListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindScope(getOrCreateScope(AVENGER_LIST_PRESENTER))
        avengersListPresenter.attachView(this, lifecycle)

        createAvengersListAdapter()
        avengersListPresenter.loadAvengers()
    }

    private fun createAvengersListAdapter() {
        adapter = AvengersListAdapter()
        adapter?.setOnAvengerListItemClickedListener(this)
        avenger_list_recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        avenger_list_recyclerview.itemAnimator = DefaultItemAnimator()
        avenger_list_recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        avenger_list_recyclerview.adapter = adapter
    }

    override fun onAvengersListLoaded(avengersList: List<Result>) {
        Timber.d("Avengers list loaded")
        adapter?.setAvengersList(avengersList)
    }

    override fun onAvengerListItemClicked(avengerModel: Result?) {
        avengerModel?.let {
            Timber.d("avenger name: " + it.name)
            val intent = Intent(applicationContext, AvengerDetailActivity::class.java)
            val imageUrl = it.thumbnail?.path + "." + it.thumbnail?.extension

            val avenger = Avenger(
                    (it.name.orEmpty()),
                    (it.modified.orEmpty()),
                    (imageUrl),
                    (it.description.orEmpty())
            )

            intent.putExtra(AVENGER_KEY, avenger)
            startActivity(intent)
        }
    }
}
