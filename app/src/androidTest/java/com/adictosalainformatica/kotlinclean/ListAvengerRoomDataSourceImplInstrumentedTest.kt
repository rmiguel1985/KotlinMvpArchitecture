package com.adictosalainformatica.kotlinclean

import android.content.Context
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl.ListAvengerRetrofitDataSourceImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.ListAvengerRoomDataSourceImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema.AppDatabase
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl.ListAvengerRepositoryCloudWithCachePolicyImpl
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockkObject
import junit.framework.Assert.assertFalse
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListAvengerRoomDataSourceImplInstrumentedTest {

    private var context: Context? = null
    private lateinit var appDatabase: AppDatabase
    private lateinit var roomDiskDataSource: ListAvengerDiskDataSource
    private lateinit var cloudWithCachePolicyImpl: ListAvengerRepositoryPolicy
    private lateinit var mockWebServer: MockWebServer
    private lateinit var listAvengerRetrofitDataSourceImpl: ListAvengerRetrofitDataSourceImpl
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        gson = Gson()
        mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer = MockWebServer()
        context = InstrumentationRegistry.getContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context!!,
                AppDatabase::class.java).build()
        roomDiskDataSource = ListAvengerRoomDataSourceImpl(appDatabase.listAvengerDao())
        mockkObject(ConnectivityHelper)

        listAvengerRetrofitDataSourceImpl =
                ListAvengerRetrofitDataSourceImpl(createRetrofitInstance(gson))
        cloudWithCachePolicyImpl =
                ListAvengerRepositoryCloudWithCachePolicyImpl(listAvengerRetrofitDataSourceImpl,
                        roomDiskDataSource )
    }

    private fun createRetrofitInstance(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mockWebServer.url("/"))
                .client(OkHttpClient.Builder().build())
                .build()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        appDatabase.close()
    }

    @Test
    fun getAvengersList_on_empty_database_returns_null() {
        //Given
        every { ConnectivityHelper.isConnected } returns (false)

        //When
        cloudWithCachePolicyImpl.getAvengersList()
        val avengersList = appDatabase.listAvengerDao().getAll()

        //Then
        assert(avengersList.isEmpty())
    }

    @Test
    fun getAvengersList_with_saved_cached_data_returns_expected_value() {
        //Given
        every { ConnectivityHelper.isConnected} returns (true)
        mockWebServer.enqueue(
                MockResponse()
                        .setBody(readAsset("json/avengers_list.json")))

        //When
        cloudWithCachePolicyImpl.getAvengersList()

        //Then
        assertFalse(appDatabase.listAvengerDao().getAll().isEmpty())
    }

    /**
     * Read file from assets
     *
     * @param assetName
     * @return string from file
     */
    @Throws(IOException::class)
    private fun readAsset(assetName: String): String {
        val `is` = context!!.assets.open(assetName)

        val size = `is`.available()

        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer)
    }
}
