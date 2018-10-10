package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result

interface AvengersListPresenterView {
    fun showProgress()

    fun hideProgress()

    fun onAvengersListLoaded(fileName: List<Result>)

    fun showErrorLoadingAvengersList()
}