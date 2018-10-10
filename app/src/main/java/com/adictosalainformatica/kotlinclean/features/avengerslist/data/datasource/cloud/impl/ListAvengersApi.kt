package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListAvengersApi {
    @GET("/v1/public/characters?ts=3&apikey=abc291f3438756b034e500e2369daeed&hash=8b24b14edd19d788fe0bea88fcc40bd3")
    abstract fun getCharacters(@Query("limit") limit: Int): Call<AvengersModel>
}