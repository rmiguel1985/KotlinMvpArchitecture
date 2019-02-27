package com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.impl


import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.ListAvengersRepository
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

class ListAvengersRepositoryImpl(private val listAvengerRepositoryPolicy: ListAvengerRepositoryPolicy) : ListAvengersRepository {

    override fun getAvengersList(): AvengersModel? {

        listAvengerRepositoryPolicy.getAvengersList()?.let {
            return it
        } ?:kotlin.run {
            throw Exception("Hi There!")
        }
    }
}
