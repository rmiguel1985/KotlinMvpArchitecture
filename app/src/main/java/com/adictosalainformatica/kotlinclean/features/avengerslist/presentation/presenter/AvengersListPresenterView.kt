package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import com.adictosalainformatica.kotlinclean.base.presentation.presenter.BasePresenterViewInterface
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result

interface AvengersListPresenterView: BasePresenterViewInterface {
    fun onAvengersListLoaded(fileName: List<Result>)
}