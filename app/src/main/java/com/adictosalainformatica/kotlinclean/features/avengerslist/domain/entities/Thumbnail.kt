package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnail (
    @SerializedName("path")
    @Expose
    var path: String? = null,
    @SerializedName("extension")
    @Expose
    var extension: String? = null)
