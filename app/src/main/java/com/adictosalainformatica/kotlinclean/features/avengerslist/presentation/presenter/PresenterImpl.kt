package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import com.adictosalainformatica.kotlinclean.base.domain.usecase.BaseUseCase
import com.adictosalainformatica.kotlinclean.base.presentation.presenter.BasePresenter
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.LoadAvengersListUseCaseImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import timber.log.Timber

class PresenterImpl(private val useCaseAvenger: LoadAvengersListUseCaseImpl): BasePresenter<AvengersListPresenterView>(){

    init {
        (useCaseAvenger as BaseUseCase<Any>).autoClear()
    }

    fun loadAvengers () {
        useCaseAvenger.execute({ avenger: AvengersModel ->
            view()?.onAvengersListLoaded(avenger.data!!.results!!)
            view()?.hideProgress()
        }, { throwable ->
            view()?.hideProgress()
            view()?.showErrorLoadingAvengersList()
            Timber.e(throwable.message)
        })
    }
}