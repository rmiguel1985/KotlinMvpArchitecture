package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

interface ListAvengerDiskDataSource {
    fun getAvengersList(): AvengersModel?
    fun setAvengers(avengers: AvengersModel)
}