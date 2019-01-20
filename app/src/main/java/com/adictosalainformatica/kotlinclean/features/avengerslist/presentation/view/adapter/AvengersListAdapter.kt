package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import java.lang.ref.WeakReference

class AvengersListAdapter : RecyclerView.Adapter<AvengersListItemViewHolder>() {
    private var avengers:List<Result>? = null
    private var clickedListenerRef: WeakReference<OnAvengerListItemClickedListener>? = null

    fun setOnAvengerListItemClickedListener(listener:OnAvengerListItemClickedListener) {
        clickedListenerRef = WeakReference(listener)
    }

    interface OnAvengerListItemClickedListener {
        fun onAvengerListItemClicked(avengerModel: Result?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): AvengersListItemViewHolder {
    val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_avengers_list_item, parent, false)

    val holder = AvengersListItemViewHolder(itemView, parent)

    itemView.setOnClickListener {
        clickedListenerRef?.get()?.onAvengerListItemClicked(avengers?.get(holder.adapterPosition))
    }

        return holder
    }

    fun setAvengersList(avengers:List<Result>) {
        this.avengers = avengers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder:AvengersListItemViewHolder, position:Int) {
        val imageUrl = avengers!![position].thumbnail!!.path + "." +
                avengers!![position].thumbnail!!.extension

        holder.decorate(avengers!![position].name!!, imageUrl)
    }

    override fun getItemCount():Int {
        return avengers?.size ?:0
    }
}