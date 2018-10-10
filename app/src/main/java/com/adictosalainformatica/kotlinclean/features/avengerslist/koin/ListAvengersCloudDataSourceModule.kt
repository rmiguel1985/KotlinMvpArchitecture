package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.ListAvengerCloudDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.cloud.impl.ListAvengerRetrofitDataSourceImpl
import org.koin.dsl.module.module

val ListAvengersCloudDataSourceModule = module(override=true) {
    single< ListAvengerCloudDataSource> { ListAvengerRetrofitDataSourceImpl(get()) }
}