package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.ListAvengerRoomDataSourceImpl
import org.koin.dsl.module.module

val ListAvengersDiskDatasourcesModule = module {
    //single<ListAvengerDiskDataSource> { ListAvengerRealmDataSourceImpl() }
    single<ListAvengerDiskDataSource> { ListAvengerRoomDataSourceImpl() }
}