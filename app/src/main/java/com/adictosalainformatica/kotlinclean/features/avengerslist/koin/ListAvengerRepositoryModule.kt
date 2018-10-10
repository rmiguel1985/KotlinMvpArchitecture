package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.ListAvengersRepository
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.impl.LisAvengersRepositoryImpl
import org.koin.dsl.module.module

val ListAvengerRepositoryModule = module(override=true) {
    single<ListAvengersRepository>{ LisAvengersRepositoryImpl(get()) }
}