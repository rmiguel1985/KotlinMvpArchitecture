package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Stories(

    @SerializedName("available")
    @Expose
    var available: Int? = null,
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null,
    @SerializedName("items")
    @Expose
    var items: List<Item__>? = null,
    @SerializedName("returned")
    @Expose
    var returned: Int? = null)
