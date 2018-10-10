package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel

interface ListAvengerCloudDataSource {
    fun getAvengersList(): AvengersModel?
}