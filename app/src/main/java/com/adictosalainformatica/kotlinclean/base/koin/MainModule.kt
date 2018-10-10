package com.adictosalainformatica.kotlinclean.base.koin

import com.adictosalainformatica.kotlinclean.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private var baseUrl: String = BuildConfig.API_BASE_URL + ":" + BuildConfig.API_PORT + BuildConfig.API_URL
private const val TIMEOUT_CONNECT_MS: Long = 8000
private const val TIMEOUT_READ_MS: Long = 8000

val MainModule = module(override = true) {

    single { Gson() }

    single {
        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ_MS, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            //OKHHTP LOGGER
            var interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

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
