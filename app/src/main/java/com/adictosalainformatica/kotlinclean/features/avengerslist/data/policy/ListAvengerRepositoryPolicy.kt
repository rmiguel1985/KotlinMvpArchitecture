package com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

interface ListAvengerRepositoryPolicy {
    fun getAvengersList(): AvengersModel?
}