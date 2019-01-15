package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter.ListAvengerPresenterImpl
import com.adictosalainformatica.kotlinclean.utils.Constants.AVENGER_LIST_PRESENTER
import org.koin.dsl.module.module

val ListAvengerPresenterModule = module {
    scope(AVENGER_LIST_PRESENTER) { ListAvengerPresenterImpl(get()) }
}