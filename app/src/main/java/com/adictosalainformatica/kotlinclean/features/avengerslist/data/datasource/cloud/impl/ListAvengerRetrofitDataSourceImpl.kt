package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.utils.Constants.API_END_POINT
import com.adictosalainformatica.kotlinclean.utils.Constants.API_HASH
import com.adictosalainformatica.kotlinclean.utils.Constants.API_KEY
import com.adictosalainformatica.kotlinclean.utils.Constants.LIMIT
import com.adictosalainformatica.kotlinclean.utils.Constants.TIME_STAMP
import com.google.gson.stream.MalformedJsonException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

/**
 * ListAvengerRetrofitDataSourceImpl
 *
 *
 * Cloud Data Source Class that gets avengers list with Retrofit
 */
class ListAvengerRetrofitDataSourceImpl(private val retrofitInstance: Retrofit) : ListAvengerCloudDataSource {

    private lateinit var response: Response<AvengersModel>

    override fun getAvengersList(): AvengersModel? {
        val call = retrofitInstance
                .create<ListAvengersApi>(ListAvengersApi::class.java)
                .getCharacters(API_END_POINT, LIMIT, API_KEY, TIME_STAMP, API_HASH)

        Timber.d("url: " + call.request().url().toString())
        try {
            response = call.execute()
        } catch (malformedJsonException: MalformedJsonException) {
            Timber.e(malformedJsonException.message)
            return null
        } catch (exception: IOException) {
            Timber.e(exception.message)
            return null
        }

        return if (response.isSuccessful) {
            response.body()
        } else {
            Timber.e("Error(${response.code()}): ${response.errorBody()}")
            null
        }
    }
}