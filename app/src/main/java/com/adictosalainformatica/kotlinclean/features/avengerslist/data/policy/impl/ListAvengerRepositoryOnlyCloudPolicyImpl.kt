package com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

/**
 * ListAvengerRepositoryOnlyCloudPolicyImpl Class
 *
 *
 * Cloud Policy Class
 */
class ListAvengerRepositoryOnlyCloudPolicyImpl
(private val listAvengerCloudDataSource: ListAvengerCloudDataSource): ListAvengerRepositoryPolicy {

    /**
     * Get Avengers List from cloud
     *
     * @return AvengersModel avengers
     */
    override fun getAvengersList(): AvengersModel? {

        /*if(ConnectivityHelper.isConnected()){
            avengers = listAvengerCloudDataSource.getAvengersList();
            //listAvengerDiskDataSource.setAvengers(avengers);
        }*/

        return listAvengerCloudDataSource.getAvengersList()
    }
}
