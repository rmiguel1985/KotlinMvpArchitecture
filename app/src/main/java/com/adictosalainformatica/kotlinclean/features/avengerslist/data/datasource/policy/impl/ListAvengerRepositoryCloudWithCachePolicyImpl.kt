package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper
import timber.log.Timber

/**
 * ListAvengerRepositoryCloudWithCachePolicyImpl Class
 *
 *
 * Cloud Policy Class with Cache
 */
class ListAvengerRepositoryCloudWithCachePolicyImpl(private val listAvengerCloudDataSource: ListAvengerCloudDataSource,
                                                         private val listAvengerDiskDataSource: ListAvengerDiskDataSource)
    : ListAvengerRepositoryPolicy {

    /**
     * Get Avengers List from cloud if it possible, otherwise from disk
     *
     * @return AvengersModel avengers
     */
    override fun getAvengersList(): AvengersModel? {
        if (ConnectivityHelper.isConnected) {
            Timber.d("Connection")
            val avengersList = listAvengerCloudDataSource.getAvengersList()

            avengersList?.data?.results?.let {
                if (it.isNotEmpty()) {
                    listAvengerDiskDataSource.setAvengers(avengersList)
                }
            }

            return avengersList
        } else {
            Timber.d("Not connection")
            return listAvengerDiskDataSource.getAvengersList()
        }
    }
}
