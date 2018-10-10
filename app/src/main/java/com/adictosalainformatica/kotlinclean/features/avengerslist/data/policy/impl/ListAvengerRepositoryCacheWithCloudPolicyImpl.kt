package com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl


import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper

/**
 * ListAvengerRepositoryCacheWithCloudPolicyImpl Class
 *
 *
 * Cache Policy Class with Cloud
 */
class ListAvengerRepositoryCacheWithCloudPolicyImpl(private val listAvengerCloudDataSource: ListAvengerCloudDataSource,
                                                    private val listAvengerDiskDataSource: ListAvengerDiskDataSource): ListAvengerRepositoryPolicy {

    /**
     * Get Avengers List from disk if it possible, otherwise from cloud
     *
     * @return AvengersModel avengers
     */
    override fun getAvengersList(): AvengersModel? {

        var avengers: AvengersModel? = listAvengerDiskDataSource.getAvengersList()

        avengers?.let {
            if (ConnectivityHelper.isConnected) {
                avengers = listAvengerCloudDataSource.getAvengersList()
                avengers?.let { listAvengerDiskDataSource.setAvengers(avengers!!) }
            }
        }

        return avengers
    }
}