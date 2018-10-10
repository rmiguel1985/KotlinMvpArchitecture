package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.PresenterImpl
import org.koin.dsl.module.module

val ListAvengerPresenterModule = module {
    factory { PresenterImpl(get()) }
}