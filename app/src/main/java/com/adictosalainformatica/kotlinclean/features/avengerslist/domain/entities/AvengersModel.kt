package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvengersModel (

    @SerializedName("code")
    @Expose
    val code: Int? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("copyright")
    @Expose
    val copyright: String? = null,
    @SerializedName("attributionText")
    @Expose
    val attributionText: String? = null,
    @SerializedName("attributionHTML")
    @Expose
    val attributionHTML: String? = null,
    @SerializedName("etag")
    @Expose
    val etag: String? = null,
    @SerializedName("data")
    @Expose
    var data: Data? = null

)
