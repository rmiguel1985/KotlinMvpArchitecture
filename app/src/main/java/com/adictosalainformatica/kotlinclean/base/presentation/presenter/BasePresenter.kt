package com.adictosalainformatica.kotlinclean.base.presentation.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper
import com.adictosalainformatica.kotlinclean.base.domain.usecase.BaseUseCase
import kotlinx.coroutines.experimental.Job

abstract class BasePresenter<View> : ViewModel(), LifecycleObserver {

    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private val disposable = Job()
    protected var useCase: BaseUseCase<Any>? = null

    fun attachView(view: View, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        useCase?.unsubscribe()
    }

    protected fun BaseUseCase<Any>.autoClear() {
        useCase = this
    }
}