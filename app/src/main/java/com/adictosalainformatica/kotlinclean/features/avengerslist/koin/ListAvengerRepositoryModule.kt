package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.ListAvengersRepository
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.impl.ListAvengersRepositoryImpl
import org.koin.dsl.module.module

val ListAvengerRepositoryModule = module(override=true) {
    single<ListAvengersRepository>{ ListAvengersRepositoryImpl(get()) }
}