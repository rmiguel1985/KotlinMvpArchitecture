package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comics (
    @SerializedName("available")
    @Expose
    val available: Int? = null,
    @SerializedName("collectionURI")
    @Expose
    val collectionURI: String? = null,
    @SerializedName("items")
    @Expose
    val items: List<Item>? = null,
    @SerializedName("returned")
    @Expose
    val returned: Int? = null)


