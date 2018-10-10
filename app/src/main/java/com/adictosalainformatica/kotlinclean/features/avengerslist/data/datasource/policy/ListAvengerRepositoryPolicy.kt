package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

interface ListAvengerRepositoryPolicy {
    fun getAvengersList(): AvengersModel?
}