package com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl.ListAvengerRetrofitDataSourceImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.ListAvengerRoomDataSourceImpl
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException


class ListAvengerRepositoryCloudWithCachePolicyImplTest {

    private val listAvengerDiskDataSource =
            mockk<ListAvengerRoomDataSourceImpl>()

    private val listAvengerCloudDataSourceImpl =
            mockk<ListAvengerRetrofitDataSourceImpl>()

    private lateinit var listAvengerRepositoryCloudWithCachePolicyImpl: ListAvengerRepositoryCloudWithCachePolicyImpl

    @Before
    fun setUp() {
        mockkObject(ConnectivityHelper)
        listAvengerRepositoryCloudWithCachePolicyImpl = ListAvengerRepositoryCloudWithCachePolicyImpl(
                listAvengerCloudDataSourceImpl, listAvengerDiskDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAvengersList_without_connectivity_calls_disk_data_source() {
        //Given
        every { listAvengerDiskDataSource.getAvengersList() } returns null
        every {ConnectivityHelper.isConnected} returns (false)

        //When
        listAvengerRepositoryCloudWithCachePolicyImpl.getAvengersList()

        //Then
        verify {listAvengerDiskDataSource.getAvengersList()}
    }

    @Test
    @Throws(IOException::class)
    fun getAvengersList_with_connectivity_calls_cloud_data_source() {
        //Given
        every { listAvengerCloudDataSourceImpl.getAvengersList() } returns null
        every {ConnectivityHelper.isConnected} returns (true)

        //When
        listAvengerRepositoryCloudWithCachePolicyImpl.getAvengersList()

        //Then
        verify {listAvengerCloudDataSourceImpl.getAvengersList()}
    }
}