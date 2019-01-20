package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_avengers_list_item.view.*

class AvengersListItemViewHolder(itemView: View, override val containerView: View?) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    fun decorate(avengerName: String?, imageUrl: String?) {
        itemView.avengers_list_item_txt.text = avengerName
        Picasso.with(itemView.context)
                .load(imageUrl)
                .resize(200, 200)
                .transform(CropCircleTransformation())
                .into(itemView.avengers_list_item_img)
    }
}