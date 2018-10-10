package com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result (

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("modified")
    @Expose
    var modified: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null,
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null,
    @SerializedName("comics")
    @Expose
    var comics: Comics? = null,
    @SerializedName("series")
    @Expose
    var series: Series? = null,
    @SerializedName("stories")
    @Expose
    var stories: Stories? = null,
    @SerializedName("events")
    @Expose
    var events: Events? = null,
    @SerializedName("urls")
    @Expose
    var urls: List<Url>? = null)
