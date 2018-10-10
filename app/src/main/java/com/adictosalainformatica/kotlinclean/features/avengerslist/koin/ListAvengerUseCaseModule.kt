package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.LoadAvengersListUseCaseImpl
import org.koin.dsl.module.module

val ListAvengerUseCaseModule = module {
    single { LoadAvengersListUseCaseImpl(get()) }
}