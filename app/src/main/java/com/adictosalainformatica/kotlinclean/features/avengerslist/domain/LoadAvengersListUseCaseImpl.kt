package com.adictosalainformatica.kotlinclean.features.avengerslist.domain

import com.adictosalainformatica.kotlinclean.base.domain.usecase.BaseUseCase
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.ListAvengersRepository
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

class LoadAvengersListUseCaseImpl(private val listAvengerRepository: ListAvengersRepository): BaseUseCase<AvengersModel>() {

    override suspend fun executeOnBackground(): AvengersModel {
        return listAvengerRepository.avengersList
    }
}