package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.repository.impl


import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.repository.ListAvengersRepository
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

class LisAvengersRepositoryImpl(private val listAvengerRepositoryPolicy: ListAvengerRepositoryPolicy) : ListAvengersRepository {

    override fun getAvengersList(): AvengersModel? {

        return listAvengerRepositoryPolicy.getAvengersList()
    }
}
