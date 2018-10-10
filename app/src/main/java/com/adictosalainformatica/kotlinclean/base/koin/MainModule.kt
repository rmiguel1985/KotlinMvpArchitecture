package com.adictosalainformatica.kotlinclean.base.koin

import android.content.Context
import androidx.room.Room
import com.adictosalainformatica.kotlinclean.BuildConfig
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private var baseUrl: String = BuildConfig.API_URL + ":" + BuildConfig.API_PORT
private val TIMEOUT_CONNECT_MS: Long = 8000
private val TIMEOUT_READ_MS: Long = 8000

val MainModule = module {

    single { Gson() }

    single {
        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ_MS, TimeUnit.MILLISECONDS)

        /*if (BuildConfig.DEBUG) {
            //OKHHTP LOGGER
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }*/

        client.build()
    }

    single {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(get()))
                .baseUrl(baseUrl)
                .client(get())
                .build()
    }

}
