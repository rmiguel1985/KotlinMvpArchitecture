package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.base.koin.MainModule

val avengersListMainModule = listOf(
        MainModule,
        ListAvengerPoliciesModule,
        ListAvengerPresenterModule,
        ListAvengerRepositoryModule,
        ListAvengersCloudDataSourceModule,
        ListAvengerUseCaseModule,
        ListAvengersDiskDatasourcesModule)