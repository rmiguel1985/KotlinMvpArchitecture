package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null)
