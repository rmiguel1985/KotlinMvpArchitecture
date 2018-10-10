package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Url (
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null)
