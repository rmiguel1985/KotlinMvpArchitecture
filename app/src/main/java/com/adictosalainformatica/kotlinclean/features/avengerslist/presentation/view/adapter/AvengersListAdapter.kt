package com.adict

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adictosalainformatica.kotlinclean.R
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view.adapter.AvengersListItemViewHolder
import java.lang.ref.WeakReference

class AvengersListAdapter(private val context: Context): RecyclerView.Adapter<AvengersListItemViewHolder>() {
    private var avengers:List<Result>? = null
    private var clickedListenerRef: WeakReference<OnAvengerListItemClickedListener>? = null

    fun setOnAvengerListItemClickedListener(listener:OnAvengerListItemClickedListener) {
        clickedListenerRef = WeakReference(listener)
    }

    interface OnAvengerListItemClickedListener {
        fun onAvengerListItemClicked(avengerModel:Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): AvengersListItemViewHolder {
    val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_avengers_list_item, parent, false)

    val holder = AvengersListItemViewHolder(itemView, parent)

    itemView.setOnClickListener { view->
        if (clickedListenerRef != null) {
            val listener = clickedListenerRef!!.get()
            listener?.onAvengerListItemClicked(avengers!![holder.adapterPosition])
        }
    }


        return holder
    }

    fun setAvengersList(avengers:List<Result>) {
        this.avengers = avengers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder:AvengersListItemViewHolder, position:Int) {
        holder.decorate(avengers!![position].name!!,
                avengers!![position].thumbnail!!.path + "." +
                avengers!![position].thumbnail!!.extension, context)
    }

    override fun getItemCount():Int {
        return avengers?.size ?:0
    }
}