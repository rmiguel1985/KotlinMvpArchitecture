package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.google.gson.Gson
import junit.framework.Assert.*
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream

class ListAvengerRetrofitDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var listAvengerRetrofitDataSourceImpl:ListAvengerRetrofitDataSourceImpl
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        gson = Gson()
        listAvengerRetrofitDataSourceImpl =
                ListAvengerRetrofitDataSourceImpl(createRetrofitInstance(gson))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun createRetrofitInstance(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mockWebServer.url("/"))
                .client(OkHttpClient.Builder().build())
                .build()
    }

    @Test
    @Throws(Exception::class)
    fun getAvengersList_with_cloud_on_successful_response_returns_and_saves_expected_json() {
        //Given
        mockWebServer.enqueue(
                MockResponse()
                        .setBody(readAsset("json/avengers_list.json")))

        //When
        val avengersList = listAvengerRetrofitDataSourceImpl.getAvengersList()

        //Then
        assertNotNull(avengersList)
        assertTrue(avengersList is AvengersModel)
        assertEquals(2, avengersList?.data?.results?.size)
        assertEquals("3-D Man", avengersList?.data?.results?.get(0)?.name)

    }


    @Test
    @Throws(Exception::class)
    fun getAvengersList_with_cloud_on_successful_response_with_malformed_json() {
        //Given
        mockWebServer.enqueue(
                MockResponse()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .addHeader("Cache-Control", "no-cache")
                        .setBody(readAsset("json/malformed_avengers_list.json")))

        //When
        val avengersList = listAvengerRetrofitDataSourceImpl.getAvengersList()

        //Then
        assertNull(avengersList)
    }

    @Test
    fun getAvengersList_with_cloud_on_unsuccessful_response() {
        //Given
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE))

        //When
        val avengersList = listAvengerRetrofitDataSourceImpl.getAvengersList()

        //Then
        assertNull(avengersList)
    }

    /**
     * Read file from assets
     *
     * @param assetName
     * @return string from file
     */
    @Throws(IOException::class)
    private fun readAsset(assetName: String): String {
        val `is`: InputStream = ClassLoader.getSystemResourceAsStream(assetName)

        val size = `is`.available()

        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer)
    }
}