package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import com.adictosalainformatica.kotlinclean.base.domain.usecase.BaseUseCase
import com.adictosalainformatica.kotlinclean.base.presentation.presenter.BasePresenter
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.LoadAvengersListUseCaseImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import timber.log.Timber

class ListAvengerPresenterImpl(private val useCaseAvenger: LoadAvengersListUseCaseImpl): BasePresenter<AvengersListPresenterView>(){

    init {
        (useCaseAvenger as BaseUseCase<Any>).autoClear()
    }

    fun loadAvengers () {
        view()?.showProgress()
        useCaseAvenger.execute {
            onComplete { avenger: AvengersModel ->
                view()?.apply {
                    hideProgress()
                    avenger.data?.results?.let {
                        onAvengersListLoaded(it)
                    } ?:kotlin.run {
                        showError("Error loading avengers list")
                    }
                }
            }

            onCancel {
                Timber.w("${useCaseAvenger.javaClass.simpleName} cancelled ")
            }

            onError { throwable ->
                view()?.apply {
                    hideProgress()
                    showError("Error loading avengers list")
                }
                Timber.e(throwable.message)
            }
        }
    }
}