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
 * <p>Cache Policy Class with Cloud</p>
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

        if (avengers == null && ConnectivityHelper.isConnected) {
                avengers = listAvengerCloudDataSource.getAvengersList()
                avengers?.let { listAvengerDiskDataSource.setAvengers(it) }
        }

        return avengers
    }
}