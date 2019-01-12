package com.adictosalainformatica.kotlinclean.base.presentation.presenter

interface BasePresenterViewInterface {
    fun showError(message: String)
    fun showMessage(message: String)
    fun showWarning(message: String)
    fun showProgress()
    fun hideProgress()
}