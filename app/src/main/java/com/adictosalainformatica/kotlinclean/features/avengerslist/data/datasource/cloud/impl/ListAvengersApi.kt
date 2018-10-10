package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.utils.Constants.API_END_POINT_PARAM
import com.adictosalainformatica.kotlinclean.utils.Constants.API_END_POINT_PARAM_GET
import com.adictosalainformatica.kotlinclean.utils.Constants.API_HASH_PARAM
import com.adictosalainformatica.kotlinclean.utils.Constants.API_KEY_PARAM
import com.adictosalainformatica.kotlinclean.utils.Constants.LIMIT_PARAM
import com.adictosalainformatica.kotlinclean.utils.Constants.TIME_STAMP_PARAM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListAvengersApi {
    @GET(API_END_POINT_PARAM_GET)
    fun getCharacters(@Path(API_END_POINT_PARAM) endPoint: String,
                      @Query(LIMIT_PARAM) limit: Int,
                      @Query(API_KEY_PARAM) key: String,
                      @Query(TIME_STAMP_PARAM) timeStamp: String,
                      @Query(API_HASH_PARAM) hash: String): Call<AvengersModel>
}