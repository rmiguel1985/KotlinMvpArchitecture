package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.adictosalainformatica.kotlinclean.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.extensions.LayoutContainer

class AvengersListItemViewHolder(itemView: View, override val containerView: View?) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    private var avengers_list_item_txt: TextView = itemView.findViewById(R.id.avengers_list_item_txt)
    private var avengers_list_item_img: ImageView = itemView.findViewById(R.id.avengers_list_item_img)

    fun decorate(avengerName: String, imageUrl: String, context: Context) {
        avengers_list_item_txt.text = avengerName
        Picasso.with(context)
                .load(imageUrl)
                .resize(200, 200)
                .transform(CropCircleTransformation())
                .into(avengers_list_item_img)
    }
}