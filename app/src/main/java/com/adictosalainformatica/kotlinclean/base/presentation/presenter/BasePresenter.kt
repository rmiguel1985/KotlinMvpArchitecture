package com.adictosalainformatica.kotlinclean.base.presentation.presenter

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.adictosalainformatica.kotlinclean.base.domain.usecase.BaseUseCase
import kotlinx.coroutines.experimental.Job
import timber.log.Timber

abstract class BasePresenter<View> : ViewModel(), LifecycleObserver {

    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private val disposable = Job()
    private var useCase: BaseUseCase<Any>? = null

    fun attachView(view: View, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle
        useCase?.unsubscribe()

        viewLifecycle.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        Timber.d("called onDestroy")

        view = null
        viewLifecycle = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Timber.d("called onPause")

        useCase?.unsubscribe()
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        Timber.d("called onCleared")
        useCase?.unsubscribe()
    }

    protected fun BaseUseCase<Any>.autoClear() {
        useCase = this
    }
}