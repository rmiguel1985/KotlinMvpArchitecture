package com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.utils.ConnectivityHelper

/**
 * ListAvengerRepositoryOnlyCloudPolicyImpl Class
 *
 * <p>Cloud Policy Class</p>
 */
class ListAvengerRepositoryOnlyCloudPolicyImpl
(private val listAvengerCloudDataSource: ListAvengerCloudDataSource): ListAvengerRepositoryPolicy {

    /**
     * Get Avengers List from cloud
     *
     * @return AvengersModel avengers
     */
    override fun getAvengersList(): AvengersModel? {
        if(ConnectivityHelper.isConnected){
            return listAvengerCloudDataSource.getAvengersList()
        }

        return null
    }
}
