package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Avenger(
    val avengerName: String,
    val avengerDateUpdate: String,
    val imageUrl: String,
    val description: String): Parcelable
